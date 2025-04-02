package ejercito.demo.infra.controller;

import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.mapper.SoldierMapper;
import ejercito.demo.infra.repository.SoldierRepository;
import ejercito.demo.models.*;
import ejercito.demo.service.Assignment.AssignmentService;
import ejercito.demo.service.Assignment.DataAssignment;
import ejercito.demo.service.soldier.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/v1/soldiers")
@Validated
public class SoldierController {

  @Autowired
  private SoldierRepository soldierRepository;

  @Autowired
  private ServiceSoldier serviceSoldier;

  @Autowired
  private AssignmentService assignmentService;

  @Autowired
  private SoldierMapper soldierMapper;

  @GetMapping
  public ResponseEntity<List<Soldier>> getSoldiersList() {
    return ResponseEntity.ok(soldierRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataAssignment> getSoldierById(@PathVariable("id") @Positive @NumberFormat Long id) throws NotFoundException {
    List<Assignment> assignmentList = assignmentService.getLisServicesByIdSoldier(id);
    Soldier soldier = serviceSoldier.getSoldierById(id);
    return ResponseEntity.ok(soldierMapper.toDataSoldierWithServices(assignmentList, soldier));
  }

  @PostMapping
  public ResponseEntity<Soldier> createSoldier(@RequestBody DataRegisterUserWithSoldier dataRegisterSoldier, UriComponentsBuilder uriComponentsBuilder) {
    Soldier soldier = serviceSoldier.createSoldierWithData(dataRegisterSoldier);
    URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(soldier.getId_soldier()).toUri();
    return ResponseEntity.created(url).body(soldier);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<DataResponseSoldier> modifySoldier(@RequestBody @Valid DataUpdateSoldier dataUpdateSoldier) {
    Soldier soldier = soldierRepository.getReferenceById(dataUpdateSoldier.id_soldier());
    soldier.updateDataSoldier(dataUpdateSoldier);
    return ResponseEntity.ok(soldierMapper.toDataSoldier(soldier));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity deleteSoldier(@PathVariable @Positive @Valid Long id) {
    serviceSoldier.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/search")
  public ResponseEntity<Soldier> searchSoldierByName(@RequestParam(value = "name") String name) {
    return ResponseEntity.ok(serviceSoldier.searchSoldierByName(name));
  }


}

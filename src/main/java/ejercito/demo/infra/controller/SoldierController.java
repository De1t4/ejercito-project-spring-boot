package ejercito.demo.infra.controller;

import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.SoldierRepository;
import ejercito.demo.models.Barrack;
import ejercito.demo.models.Body;
import ejercito.demo.models.Company;
import ejercito.demo.models.Soldier;
import ejercito.demo.service.soldier.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/soldiers")
public class SoldierController {

  @Autowired
  private SoldierRepository soldierRepository;

  @Autowired
  private ServiceSoldier serviceSoldier;

  @GetMapping
  public ResponseEntity<List<Soldier>> getSoldiersList(){
    return ResponseEntity.ok(soldierRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Soldier> getSoldierById(@PathVariable Long id) throws NotFoundException {
    return ResponseEntity.ok(serviceSoldier.getSoldierById(id));
  }

  @PostMapping
  public ResponseEntity<Soldier> createSoldier(@RequestBody @Valid DataRegisterSoldier dataRegisterSoldier, UriComponentsBuilder uriComponentsBuilder){
    Soldier soldier = soldierRepository.save(serviceSoldier.createSoldierWithData(dataRegisterSoldier));
    URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(soldier.getId_soldier()).toUri();
    return ResponseEntity.created(url).body(soldier);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<DataResponseSoldier> modifySoldier(@RequestBody @Valid DataUpdateSoldier dataUpdateSoldier){
    Soldier soldier = soldierRepository.getReferenceById(dataUpdateSoldier.id_soldier());
    soldier.updateDataSoldier(dataUpdateSoldier);
    return ResponseEntity.ok(mapDataSoldier(soldier));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity deleteSoldier(@PathVariable Long id){
    soldierRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/search")
  public ResponseEntity<Soldier> searchSoldierByName(@RequestParam(value="name") String name){
    return ResponseEntity.ok(soldierRepository.findByName(name));
  }

  private DataResponseSoldier mapDataSoldier(Soldier soldier){
    return new DataResponseSoldier(
            soldier.getId_soldier(), soldier.getName(), soldier.getLastname(), soldier.getGraduation(),
            new Company(soldier.getCompany().getId_company(), soldier.getCompany().getActivity()),
            new Barrack(soldier.getBarrack().getId_barrack(), soldier.getBarrack().getName(), soldier.getBarrack().getLocation()),
            new Body(soldier.getBody().getId_body(), soldier.getBody().getDenomination()));
  }
}

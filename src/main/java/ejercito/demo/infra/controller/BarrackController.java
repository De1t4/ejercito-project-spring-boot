package ejercito.demo.infra.controller;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.BarrackRepository;
import ejercito.demo.models.Barrack;
import ejercito.demo.service.barrack.DataRegisterBarrack;
import ejercito.demo.service.barrack.DataResponseBarrack;
import ejercito.demo.service.barrack.DataUpdateBarrack;
import ejercito.demo.service.barrack.ServiceBarrack;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Barracks")
@RequestMapping("/v1/barracks")
public class BarrackController {

  @Autowired
  private BarrackRepository barrackRepository;

  @Autowired
  private ServiceBarrack serviceBarrack;

  @GetMapping
  public ResponseEntity<List<Barrack>> getBarracksList() {
    return ResponseEntity.ok(barrackRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataResponseBarrack> getBarrackById(@PathVariable @Valid Long id) throws NotFoundException {
    return ResponseEntity.ok(createDataBarrack(serviceBarrack.getBarrackById(id)));
  }

  @PostMapping
  public ResponseEntity<DataResponseBarrack> createBarrack(@RequestBody DataRegisterBarrack dataRegisterBarrack, UriComponentsBuilder uriComponentsBuilder) {
    Barrack barrack = serviceBarrack.createBarrackWithData(dataRegisterBarrack);
    URI url = uriComponentsBuilder.path("/companies/{id}").buildAndExpand(barrack.getId_barrack()).toUri();
    return ResponseEntity.created(url).body(createDataBarrack(barrack));
  }

  @PutMapping
  @Transactional
  public ResponseEntity<DataResponseBarrack> modifyBarrack(@RequestBody DataUpdateBarrack dataUpdateBarrack) throws BadRequestException {
    Barrack barrack = serviceBarrack.updateDataBarrack(dataUpdateBarrack);
    return ResponseEntity.ok(createDataBarrack(barrack));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity deleteBarrack(@PathVariable @NotNull Long id) {
    serviceBarrack.deleteBarrackById(id);
    return ResponseEntity.noContent().build();
  }

  private DataResponseBarrack createDataBarrack(Barrack barrack) {
    return new DataResponseBarrack(barrack.getId_barrack(), barrack.getName(), barrack.getLocation());
  }
}

package ejercito.demo.infra.controller;

import ejercito.demo.infra.repository.BodyRepository;
import ejercito.demo.models.Body;
import ejercito.demo.service.body.DataRegisterBody;
import ejercito.demo.service.body.DataResponseBody;
import ejercito.demo.service.body.DataUpdateBody;
import ejercito.demo.service.body.ServiceBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/v1/bodies")
@Tag(name = "Army Bodies")
public class BodyController {

  @Autowired
  private BodyRepository bodyRepository;

  @Autowired
  private ServiceBody serviceBody;

  @GetMapping
  public ResponseEntity<List<Body>> getBodiesArmyList() {
    return ResponseEntity.ok(bodyRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataResponseBody> getBodyById(@PathVariable Long id) {
    return ResponseEntity.ok(createDataBodyArmy(serviceBody.getBodyById(id)));
  }

  @PostMapping
  public ResponseEntity<Body> createBodyArmy(@RequestBody DataRegisterBody dataRegisterBody, UriComponentsBuilder uriComponentsBuilder) {
    Body body = serviceBody.createBodyArmy(dataRegisterBody);
    URI url = uriComponentsBuilder.path("/bodies/{id}").buildAndExpand(body.getId_body()).toUri();
    return ResponseEntity.created(url).body(body);
  }

  @PutMapping
  public ResponseEntity<DataResponseBody> modifyBodyArmy(@RequestBody DataUpdateBody dataUpdateBody) {
    return ResponseEntity.ok(createDataBodyArmy(serviceBody.modifyBodyArmy(dataUpdateBody)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ProjectInfoProperties.Build> deleteBodyArmy(@PathVariable Long id) {
    serviceBody.deleteBodyArmy(id);
    return ResponseEntity.noContent().build();
  }

  private DataResponseBody createDataBodyArmy(Body body) {
    return new DataResponseBody(body.getId_body(), body.getDenomination());
  }
}

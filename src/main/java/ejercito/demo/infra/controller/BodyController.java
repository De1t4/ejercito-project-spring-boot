package ejercito.demo.infra.controller;

import ejercito.demo.infra.repository.BodyRepository;
import ejercito.demo.models.Body;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/bodies")
public class BodyController {

  @Autowired
  private BodyRepository bodyRepository;

  @GetMapping
  public ResponseEntity<List<Body>> getBodiesArmyList(){
    return ResponseEntity.ok(bodyRepository.findAll());
  }


}

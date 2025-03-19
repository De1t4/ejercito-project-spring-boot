package ejercito.demo.infra.controller;

import ejercito.demo.infra.repository.BodyRepository;
import ejercito.demo.models.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bodies")
public class BodyController {

  @Autowired
  private BodyRepository bodyRepository;

  @GetMapping
  public ResponseEntity<List<Body>> getBodiesArmyList(){
    return ResponseEntity.ok(bodyRepository.findAll());
  }


}

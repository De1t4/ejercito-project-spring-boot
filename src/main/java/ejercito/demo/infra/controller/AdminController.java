package ejercito.demo.infra.controller;

import ejercito.demo.service.admin.DataGeneralAdmin;
import ejercito.demo.service.admin.ServiceAdmin;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/v1/admin")
public class AdminController {

  @Autowired
  public ServiceAdmin serviceAdmin;

  @GetMapping("/general-data")
  private ResponseEntity<DataGeneralAdmin> getDataGeneralSystem(){
    return ResponseEntity.ok(serviceAdmin.getDataGeneralSystem());
  }

}

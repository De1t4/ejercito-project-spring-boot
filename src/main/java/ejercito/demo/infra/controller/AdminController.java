package ejercito.demo.infra.controller;

import ejercito.demo.service.admin.DataGeneralAdmin;
import ejercito.demo.service.admin.DataGeneralSoldiersAdmin;
import ejercito.demo.service.admin.DataStructureAdmin;
import ejercito.demo.service.admin.ServiceAdmin;
import ejercito.demo.service.assignment.dto.response.DataAllServicesAssignment;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

  @GetMapping("/general-data-structure")
  private ResponseEntity<DataStructureAdmin> getDataStructure(){
    return ResponseEntity.ok(serviceAdmin.getDataStructure());
  }

  @GetMapping("/general-data-soldiers")
  private ResponseEntity<Page<DataGeneralSoldiersAdmin>> getDataSoldier(@PageableDefault(size = 10) Pageable pageable){
    Page<DataGeneralSoldiersAdmin> dataGeneralSoldiers = serviceAdmin.getAllSoldiersData(pageable);
    return ResponseEntity.ok(dataGeneralSoldiers);
  }
}

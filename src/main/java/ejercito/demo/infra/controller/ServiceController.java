package ejercito.demo.infra.controller;

import ejercito.demo.infra.repository.ServiceRepository;
import ejercito.demo.models.Services;
import ejercito.demo.service.service.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Services")
@RequestMapping("/v1/services")
public class ServiceController {

  @Autowired
  private ServiceRepository serviceRepository;

  @Autowired
  private ServiceSoldierServices serviceSoldierServices;

  @GetMapping
  public ResponseEntity<List<DataListResponseService>> getServicesList() {
    return ResponseEntity.ok(createDataListServices(serviceRepository.findAll()));
  }


  @GetMapping("/{id}")
  public ResponseEntity<DataResponseService> getServiceById(@PathVariable Long id) {
    return ResponseEntity.ok(createDataService(serviceSoldierServices.getServiceById(id)));
  }

  @PostMapping
  public ResponseEntity<DataResponseService> createServiceWithSoldier(@RequestBody DataRegisterService dataRegisterService, UriComponentsBuilder uriComponentsBuilder) {
    Services services = serviceSoldierServices.createService(dataRegisterService);
    URI url = uriComponentsBuilder.path("/services/{id}").buildAndExpand(services.getId_service()).toUri();
    return ResponseEntity.created(url).body(createDataService(services));
  }


  @PutMapping
  @Transactional
  public ResponseEntity<DataResponseService> modifyServiceWithSolder(@RequestBody DataUpdateServices dataUpdateServices) {
    return ResponseEntity.ok(createDataService(serviceSoldierServices.modifyServices(dataUpdateServices)));
  }


  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<ProjectInfoProperties.Build> deleteService(@PathVariable Long id) {
    serviceSoldierServices.deleteServiceById(id);
    return ResponseEntity.noContent().build();
  }

  private List<DataListResponseService> createDataListServices(List<Services> services) {
    ArrayList<DataListResponseService> newListServices = new ArrayList<DataListResponseService>();
    for (Services service : services) {
      newListServices.add(new DataListResponseService(service.getId_service(), service.getDescription()));
    }
    return newListServices;
  }


  private DataResponseService createDataService(Services services) {
    return new DataResponseService(services.getId_service(), services.getDescription());
  }
}

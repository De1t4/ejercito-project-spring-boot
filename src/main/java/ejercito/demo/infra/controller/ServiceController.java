package ejercito.demo.infra.controller;

import ejercito.demo.infra.repository.ServiceRepository;
import ejercito.demo.models.Service;
import ejercito.demo.service.service.DataResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

  @Autowired
  private ServiceRepository serviceRepository;

  @GetMapping
  public ResponseEntity<List<DataResponseService>> getServicesList(){
    return ResponseEntity.ok(createDataListServices(serviceRepository.findAll()));
  }

  private List<DataResponseService> createDataListServices(List<Service> services){
    ArrayList<DataResponseService> newListServices = new ArrayList<DataResponseService>();
    for(Service service : services){
      newListServices.add(new DataResponseService(service.getId_service(), service.getDescription(), service.getEnd_service()));
    }
    return newListServices;
  }
}

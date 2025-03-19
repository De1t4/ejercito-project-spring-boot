package ejercito.demo.infra.controller;

import ejercito.demo.infra.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class ServiceController {

  @Autowired
  private ServiceRepository serviceRepository;


}

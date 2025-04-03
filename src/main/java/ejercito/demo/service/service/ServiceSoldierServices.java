package ejercito.demo.service.service;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.ServiceRepository;
import ejercito.demo.models.Services;
import ejercito.demo.service.soldier.ServiceSoldier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceSoldierServices {

  @Autowired
  private ServiceRepository serviceRepository;

  public Services getServiceById(Long id) {
    return findService(id);
  }

  public Services createService(DataRegisterService dataRegisterService) {
    return serviceRepository.save(new Services(dataRegisterService));
  }

  public Services modifyServices(DataUpdateServices dataUpdateServices) {
    Services services = findService(dataUpdateServices.id_service());
    services.updateDataService(dataUpdateServices);
    return services;
  }

  private Services findService(Long id) throws NotFoundException {
    return serviceRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Service with ID " + id + " not found"));
  }

  public void deleteServiceById(Long id) throws BadRequestException {
    if (findService(id) != null) {
      serviceRepository.deleteById(id);
    }
  }

}
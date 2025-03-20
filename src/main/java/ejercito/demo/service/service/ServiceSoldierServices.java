package ejercito.demo.service.service;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.ServiceRepository;
import ejercito.demo.infra.repository.SoldierRepository;
import ejercito.demo.models.Barrack;
import ejercito.demo.models.Services;
import ejercito.demo.models.Soldier;
import ejercito.demo.service.soldier.ServiceSoldier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceSoldierServices {

  @Autowired
  private ServiceRepository serviceRepository;

  @Autowired
  private ServiceSoldier serviceSoldier;

  public Services getServiceById(Long id){
    return findService(id);
  }

  private Services findService(Long id) {
    return serviceRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Service with ID " + id + " not found"));
  }

  public Services createService(DataRegisterService dataRegisterService) {
    Soldier soldier = serviceSoldier.getSoldierById(dataRegisterService.id_soldier());
    return serviceRepository.save( new Services(dataRegisterService, soldier));
  }

  public Services modifyServices(DataUpdateServices dataUpdateServices) {
    Services services = findService(dataUpdateServices.id_service());
    if(dataUpdateServices.id_soldier() != null){
      services.updateDataService(dataUpdateServices, serviceSoldier.getSoldierById(dataUpdateServices.id_soldier()));
      return services;
    }
    services.updateDataService(dataUpdateServices, null);

    return services;
  }

  public void deleteServiceById(Long id) throws BadRequestException {
    if(findService(id) != null){
      serviceRepository.deleteById(id);
    }
  }
}

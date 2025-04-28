package ejercito.demo.service.subOfficial;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.DuplicateException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.SoldierRepository;
import ejercito.demo.infra.repository.UserRepository;
import ejercito.demo.models.*;
import ejercito.demo.service.barrack.ServiceBarrack;
import ejercito.demo.service.body.ServiceBody;
import ejercito.demo.service.company.ServiceCompany;
import ejercito.demo.service.soldier.ServiceSoldier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServiceSubOfficial {

  @Autowired
  public UserRepository userRepository;

  @Autowired
  public SoldierRepository soldierRepository;

  @Autowired
  public ServiceSoldier serviceSoldier;

  public Page<DataSubOfficial> getAllSubOficialsData(Pageable pageable) {
    Page<User> usersPage = userRepository.getPageUsersSubOficials(pageable);
    return usersPage.map(user -> new DataSubOfficial(user.getId_user(), user.getUsername(), user.getSoldier() == null ? null : user.getSoldier()));
  }

  public DataSubOfficial getDataSubOfficial(Long id) {
    if (id == null) {
      throw new BadRequestException("Field id Sub Official is null");
    }
    User user = findSubOfficial(id);
    return new DataSubOfficial(user.getId_user(), user.getUsername(), user.getSoldier() == null ? null : user.getSoldier());
  }

  public User findSubOfficial(Long id_subOfficial) throws NotFoundException {
    User user = userRepository.findSubOfficialById(id_subOfficial);
    if (user == null) {
      throw new NotFoundException("Sub Official with ID " + id_subOfficial + " not found");
    }
    return user;
  }

  public User createSubOfficial(DataCreateSubOfficial dataCreateSubOfficial) {
    if(dataCreateSubOfficial == null){
      throw new BadRequestException("Data field official is empty");
    }
    if( dataCreateSubOfficial.username() == null){
      throw new BadRequestException("Field username not found");
    }
    if( dataCreateSubOfficial.password() == null){
      throw new BadRequestException("Field password not found");
    }
    if (userRepository.findByUsername(dataCreateSubOfficial.username()).isPresent()) {
      throw new DuplicateException("THE USER " + dataCreateSubOfficial.username() + " HAS ALREADY BEEN REGISTERED");
    }
    if(dataCreateSubOfficial.soldier() != null){
      Soldier soldier = serviceSoldier.createSoldierData(dataCreateSubOfficial.soldier());
      return userRepository.save(new User(dataCreateSubOfficial, soldier));
    }
    return userRepository.save(new User(dataCreateSubOfficial, null));
  }
}

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

import java.util.Set;

@Service
public class ServiceSubOfficial {

  @Autowired
  public UserRepository userRepository;

  @Autowired
  public SoldierRepository soldierRepository;

  @Autowired
  public ServiceSoldier serviceSoldier;

  @Autowired
  private ServiceBarrack serviceBarrack;

  @Autowired
  private ServiceCompany serviceCompany;

  @Autowired
  private ServiceBody serviceBody;

  public Page<DataSubOfficial> getAllSubOficialsData(Pageable pageable, String search) {
    if(search != null){
      Page<User> usersPage = userRepository.getPageSubOfficialsFounded(pageable, search);
      return usersPage.map(user -> new DataSubOfficial(user.getId_user(), user.getUsername(), user.getSoldier() == null ? null : user.getSoldier()));
    }
    Page<User> usersPage = userRepository.getPageUsersSubOficials(pageable);
    return usersPage.map(user -> new DataSubOfficial(user.getId_user(), user.getUsername(), user.getSoldier() == null ? null : user.getSoldier()));
  }

  public User findSubOfficial(Long id_subOfficial) throws NotFoundException {
    User user = userRepository.findSubOfficialById(id_subOfficial);
    if (user == null) {
      throw new NotFoundException("Sub Official with ID " + id_subOfficial + " not found");
    }
    return user;
  }

  public User createSubOfficial(DataCreateSubOfficial dataCreateSubOfficial) {
    if (dataCreateSubOfficial == null) {
      throw new BadRequestException("Data field official is empty");
    }
    if (dataCreateSubOfficial.username() == null) {
      throw new BadRequestException("Field username not found");
    }
    if (dataCreateSubOfficial.password() == null) {
      throw new BadRequestException("Field password not found");
    }
    if (userRepository.findByUsername(dataCreateSubOfficial.username()).isPresent()) {
      throw new DuplicateException("THE USER " + dataCreateSubOfficial.username() + " HAS ALREADY BEEN REGISTERED");
    }
    if (dataCreateSubOfficial.soldier() != null) {
      Soldier soldier = serviceSoldier.createSoldierData(dataCreateSubOfficial.soldier());
      return userRepository.save(new User(dataCreateSubOfficial, soldier));
    }
    return userRepository.save(new User(dataCreateSubOfficial, null));
  }

  public void deleteSubOfficials(Set<Long> ids) {
    for (Long id : ids) {
      getDataSubOfficial(id);
    }
    userRepository.deleteAllById(ids);
  }

  public DataSubOfficial getDataSubOfficial(Long id) {
    if (id == null) {
      throw new BadRequestException("Field id Sub Official is null");
    }
    User user = findSubOfficial(id);
    return new DataSubOfficial(user.getId_user(), user.getUsername(), user.getSoldier() == null ? null : user.getSoldier());
  }

  public DataSubOfficial updateSubOfficial(DataUpdateSubOfficial soldierData) {
    User user = findSubOfficial(soldierData.id_user());
    if (soldierData.username() != null) {
      if(!user.getUsername().equals(soldierData.username())){
        if (userRepository.findByUsername(soldierData.username()).isPresent()) {
          throw new DuplicateException("THE USER " + soldierData.username() + " HAS ALREADY BEEN REGISTERED");
        }
      }
      user.setUsername(soldierData.username());
    }

    if (soldierData.soldier() == null) {
      user.setSoldier(null);
      userRepository.save(user);
      return new DataSubOfficial(user.getId_user(), user.getUsername(), null);
    }

    if (user.getSoldier() == null) {
      Company company = serviceCompany.getCompanyById(soldierData.soldier().id_company());
      Barrack barrack = serviceBarrack.getBarrackById(soldierData.soldier().id_barrack());
      Body body = serviceBody.getBodyById(soldierData.soldier().id_body());
      Soldier soldier = soldierRepository.save(new Soldier(soldierData.soldier().name(), soldierData.soldier().lastname(), soldierData.soldier().graduation(), company, barrack, body));
      user.setSoldier(soldier); // Asociar el nuevo Soldier al User
      userRepository.save(user); // Guardar el User con la relación
      return new DataSubOfficial(user.getId_user(), user.getUsername(), soldier);
    } else {
      Soldier soldier = user.getSoldier();
      if (soldierData.soldier().id_barrack() != null) {
        Barrack barrack = serviceBarrack.getBarrackById(soldierData.soldier().id_barrack());
        soldier.setBarrack(barrack);
      }

      if (soldierData.soldier().id_company() != null) {
        Company company = serviceCompany.getCompanyById(soldierData.soldier().id_company());
        soldier.setCompany(company);
      }
      if (soldierData.soldier().id_body() != null) {
        Body body = serviceBody.getBodyById(soldierData.soldier().id_body());
        soldier.setBody(body);
      }
      soldier.updateDataSubOfficial(soldierData.soldier().name(), soldierData.soldier().lastname(), soldierData.soldier().graduation());
      soldierRepository.save(soldier);
      return new DataSubOfficial(soldierData.id_user(), soldierData.username(), soldier);
    }
  }
}

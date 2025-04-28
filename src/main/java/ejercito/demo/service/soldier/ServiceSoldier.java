package ejercito.demo.service.soldier;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.DuplicateException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.*;
import ejercito.demo.models.*;
import ejercito.demo.service.barrack.ServiceBarrack;
import ejercito.demo.service.body.ServiceBody;
import ejercito.demo.service.company.ServiceCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceSoldier {
  @Autowired
  private SoldierRepository soldierRepository;

  @Autowired
  private ServiceBarrack serviceBarrack;

  @Autowired
  private ServiceCompany serviceCompany;

  @Autowired
  private ServiceBody serviceBody;

  @Autowired
  private UserRepository userRepository;

  public Soldier getUserByName(String name) {
    return soldierRepository.findByName(name);
  }

  public Soldier createSoldierWithData(DataRegisterUserWithSoldier dataRegisterSoldier) {
    validateFields(dataRegisterSoldier.username(), "username");
    validateFields(dataRegisterSoldier.password(), "password");

    if (userRepository.findByUsername(dataRegisterSoldier.username()).isPresent()) {
      throw new DuplicateException("THE USER " + dataRegisterSoldier.username() + " HAS ALREADY BEEN REGISTERED");
    }

    if (dataRegisterSoldier.soldier() == null) {
      throw new BadRequestException("Not found object soldier");
    }

    validateFields(dataRegisterSoldier.soldier().name(), "name");
    validateFields(dataRegisterSoldier.soldier().lastname(), "lastname");
    validateFieldIDs(dataRegisterSoldier.soldier().id_company(), "id_company");
    validateFieldIDs(dataRegisterSoldier.soldier().id_barrack(), "id_barrack");
    validateFieldIDs(dataRegisterSoldier.soldier().id_body(), "id_body");
    Soldier soldier = createSoldierData(dataRegisterSoldier.soldier());
    userRepository.save(new User(dataRegisterSoldier, soldier));
    return soldier;
  }

  public Soldier getSoldierById(Long id) throws NotFoundException {
    return findSoldierById(id);
  }

  public Soldier searchSoldierByName(String name) throws NotFoundException {
    Soldier soldier = soldierRepository.findByName(name);
    if (soldier == null) {
      throw new NotFoundException("Soldier with name " + name + " not found");
    }
    return soldier;
  }

  private void validateFields(String field, String fieldName) {
    if (field == null || field.isEmpty()) {
      throw new BadRequestException("Field " + fieldName + " is required");
    }
  }

  private void validateFieldIDs(Long id, String fieldName) {
    if (id == null) {
      throw new BadRequestException("Field " + fieldName + " is required");
    }
  }

  public List<Soldier> getSoldiersByIds(List<Long> ids) {
    return soldierRepository.getSoldiersByIds(ids);
  }

  private Soldier findSoldierById(Long idSoldier) {
    if (idSoldier == null) {
      throw new BadRequestException("THE ID CANNOT BE NULL");
    }

    return soldierRepository.findById(idSoldier)
            .orElseThrow(() -> new NotFoundException("Soldier with ID " + idSoldier + " not found"));
  }

  public void deleteById(Long id) {
    findSoldierById(id);
    soldierRepository.deleteById(id);
  }

  public Soldier updateSoldier(DataUpdateSoldier dataUpdateSoldier) {
    Soldier soldier = findSoldierById(dataUpdateSoldier.id_soldier());
    if (dataUpdateSoldier.id_barrack() != null) {
      Barrack barrack = serviceBarrack.getBarrackById(dataUpdateSoldier.id_barrack());
      soldier.setBarrack(barrack);
    }

    if (dataUpdateSoldier.id_company() != null) {
      Company company = serviceCompany.getCompanyById(dataUpdateSoldier.id_company());
      soldier.setCompany(company);
    }
    if (dataUpdateSoldier.id_body() != null) {
      Body body = serviceBody.getBodyById(dataUpdateSoldier.id_body());
      soldier.setBody(body);
    }

    soldier.updateDataSoldier(dataUpdateSoldier);
    return soldierRepository.save(soldier);
  }

  public List<DataManageSoldier> getListSoldiersAll() {
    List<User> userList = userRepository.getListUsersSoldiers();

    return userList.stream()
            .map(user -> {
              Soldier soldier = user.getSoldier();
              if (soldier == null) {
                return new DataManageSoldier(
                        user.getId_user(),
                        null,
                        user.getUsername(),
                        null, null, null, null, null, null, null, null
                );
              } else {
                return new DataManageSoldier(
                        user.getId_user(),
                        soldier.getId_soldier(),
                        user.getUsername(),
                        soldier.getName(),
                        soldier.getLastname(),
                        soldier.getBarrack().getId_barrack(),
                        soldier.getBarrack().getName(),
                        soldier.getCompany().getId_company(),
                        soldier.getCompany().getActivity(),
                        soldier.getBody().getId_body(),
                        soldier.getBody().getDenomination()
                );
              }
            })
            .collect(Collectors.toList());
  }

  public Soldier createSoldierData (DataRegisterSoldier soldier){
    Company company = serviceCompany.getCompanyById(soldier.id_company());
    Barrack barrack = serviceBarrack.getBarrackById(soldier.id_barrack());
    Body body = serviceBody.getBodyById(soldier.id_body());
    return  soldierRepository.save(new Soldier(soldier, company, barrack, body));
  }
}

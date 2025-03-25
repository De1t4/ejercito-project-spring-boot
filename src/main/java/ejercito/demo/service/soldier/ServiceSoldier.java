package ejercito.demo.service.soldier;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.DuplicateException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.*;
import ejercito.demo.models.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Optional;

@Service
public class ServiceSoldier {
  @Autowired
  private SoldierRepository soldierRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private BarrackRepository barrackRepository;

  @Autowired
  private BodyRepository bodyRepository;

  @Autowired
  private UserRepository userRepository;


  public Soldier getUserByName(String name) {
    return soldierRepository.findByName(name);
  }

  public Soldier createSoldierWithData(DataRegisterUserWithSoldier dataRegisterSoldier){
    validateFields(dataRegisterSoldier.username(), "username");
    validateFields(dataRegisterSoldier.password(), "password");

    if (userRepository.findByUsername(dataRegisterSoldier.username()).isPresent()){
      throw new DuplicateException("THE USER " + dataRegisterSoldier.username() + " HAS ALREADY BEEN REGISTERED");
    }

    if(dataRegisterSoldier.dataRegisterSoldier() == null){
      throw new BadRequestException("Not found object dataRegisterSoldier");
    }

    validateFields(dataRegisterSoldier.dataRegisterSoldier().name(), "name");
    validateFields(dataRegisterSoldier.dataRegisterSoldier().lastname(), "lastname");
    validateFieldIDs(dataRegisterSoldier.dataRegisterSoldier().id_company(), "id_company");
    validateFieldIDs(dataRegisterSoldier.dataRegisterSoldier().id_barrack(), "id_barrack");
    validateFieldIDs(dataRegisterSoldier.dataRegisterSoldier().id_body(), "id_body");

    Company company = companyRepository.findById(dataRegisterSoldier.dataRegisterSoldier().id_company()).orElseThrow(() -> new RuntimeException("Company not found"));;
    Barrack barrack = barrackRepository.findById(dataRegisterSoldier.dataRegisterSoldier().id_barrack()).orElseThrow(() -> new RuntimeException("Barrack not found"));
    Body body = bodyRepository.findById(dataRegisterSoldier.dataRegisterSoldier().id_body()).orElseThrow(() -> new RuntimeException("Army body not found "));


    Soldier soldier = soldierRepository.save(new Soldier(dataRegisterSoldier.dataRegisterSoldier(), company, barrack, body));
    userRepository.save(new User(dataRegisterSoldier, soldier));
    return soldier;
  }

  public Soldier getSoldierById(Long id) throws NotFoundException {
    if(id == null){
      throw new BadRequestException("THE ID CANNOT BE NULL");
    }

    Optional<Soldier> soldier = soldierRepository.findById(id);
    if(!soldier.isPresent()){
      throw new NotFoundException("NOT FOUND SOLDIER WITH ID " + id);
    }
    return soldier.get();
  }

  public Soldier searchSoldierByName(String name) throws NotFoundException {
    Soldier soldier = soldierRepository.findByName(name);
    if(soldier == null){
      throw new NotFoundException("Soldier with name " + name + " not found");
    }
    return soldier;
  }

  private void validateFields(String field, String fieldName) {
    if (field == null || field.isEmpty()) {
      throw new BadRequestException("Field " + fieldName + " is required");
    }
  }

  private void validateFieldIDs(Long id, String fieldName){
    if (id == null ) {
      throw new BadRequestException("Field " + fieldName + " is required");
    }
  }

}

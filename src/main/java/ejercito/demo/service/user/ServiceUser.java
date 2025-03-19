package ejercito.demo.service.user;

import ejercito.demo.infra.errors.DuplicateException;
import ejercito.demo.infra.repository.SoldierRepository;
import ejercito.demo.infra.repository.UserRepository;
import ejercito.demo.models.Soldier;
import ejercito.demo.models.User;
import ejercito.demo.service.soldier.ServiceSoldier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class ServiceUser {

  @Autowired
  private SoldierRepository soldierRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ServiceSoldier serviceSoldier;

  public User createUser( DataRegisterUser dataRegisterUser)  throws Exception{

    if ("SOLDADO".equals(dataRegisterUser.role()) && dataRegisterUser.dataRegisterSoldier() == null) {
      throw new RuntimeException("Error: SOLDIER debe tener información de Soldier.");
    }

    if (userRepository.findByUsername(dataRegisterUser.username()).isPresent()){
      throw new DuplicateException("THE USER " + dataRegisterUser.username() + " HAS ALREADY BEEN REGISTERED");
    }

    if(dataRegisterUser.role().equals("SOLDADO")){
      Soldier soldier = soldierRepository.save(serviceSoldier.createSoldierWithData(dataRegisterUser.dataRegisterSoldier()));
      return userRepository.save(new User(soldier, dataRegisterUser));
    }

    return userRepository.save(new User(dataRegisterUser));
  }
}

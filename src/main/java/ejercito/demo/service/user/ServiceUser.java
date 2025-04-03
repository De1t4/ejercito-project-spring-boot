package ejercito.demo.service.user;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.DuplicateException;
import ejercito.demo.infra.repository.SoldierRepository;
import ejercito.demo.infra.repository.UserRepository;
import ejercito.demo.models.Soldier;
import ejercito.demo.models.User;
import ejercito.demo.service.soldier.ServiceSoldier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser {

  @Autowired
  private SoldierRepository soldierRepository;

  @Autowired
  private UserRepository userRepository;

  public User createUser(DataRegisterUser dataRegisterUser) throws Exception {
    if (userRepository.findByUsername(dataRegisterUser.username()).isPresent()) {
      throw new DuplicateException("THE USER " + dataRegisterUser.username() + " HAS ALREADY BEEN REGISTERED");
    }

    if (!dataRegisterUser.role().equals("SOLDADO") &&
            !dataRegisterUser.role().equals("OFICIAL") &&
            !dataRegisterUser.role().equals("SUB_OFICIAL")) {
      throw new BadRequestException("THE ROLE ENTERED IS NOT ALLOWED, ROLE ALLOWED (OFICIAL, SOLDADO, SUB_OFICIAL)");
    }

    return userRepository.save(new User(dataRegisterUser));
  }

}

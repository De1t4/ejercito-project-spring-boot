package ejercito.demo.service.user;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.DuplicateException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.mapper.SoldierMapper;
import ejercito.demo.infra.repository.SoldierRepository;
import ejercito.demo.infra.repository.UserRepository;
import ejercito.demo.models.Assignment;
import ejercito.demo.models.User;
import ejercito.demo.service.assignment.AssignmentService;
import ejercito.demo.service.profile.DataResponseProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ServiceUser {

  @Autowired
  private SoldierRepository soldierRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AssignmentService assignmentService;

  @Autowired
  private SoldierMapper soldierMapper;

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

  public DataResponseProfile getUserProfile( Long id_user) {
    User user = findUserById(id_user);
    if (user.getSoldier() == null) {
      return new DataResponseProfile(user.getId_user(), user.getUsername(), user.getPassword(), user.getRole(), null, null);
    }
    List<Assignment> assignmentList = assignmentService.getListServicesByIdSoldier(user.getSoldier().getId_soldier());
    return new DataResponseProfile(user.getId_user(), user.getUsername(), user.getPassword(), user.getRole(), user.getSoldier(), soldierMapper.toOrderServicesForStatus(assignmentList));
  }

  public User findUserById(Long id_user) {
    if (id_user == null) {
      throw new BadRequestException("THE ID CANNOT BE NULL");
    }

    return userRepository.findById(id_user)
            .orElseThrow(() -> new NotFoundException("User with ID " + id_user + " not found"));
  }

  public void deleteUsersList(Set<Long> idsSoldier) {
    for(Long id: idsSoldier){
      findUserById(id);
    }
    userRepository.deleteAllById(idsSoldier);
  }


 
}

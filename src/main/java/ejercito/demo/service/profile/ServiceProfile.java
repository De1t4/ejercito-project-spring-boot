package ejercito.demo.service.profile;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.AssignmentRepository;
import ejercito.demo.infra.repository.UserRepository;
import ejercito.demo.models.Assignment;
import ejercito.demo.models.User;
import ejercito.demo.service.Assignment.AssignmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProfile {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AssignmentService assignmentService;

  @Autowired
  private AssignmentRepository assignmentRepository;



  public User getProfileUser(Authentication token){
    User user = (User) token.getPrincipal();
    return findUserById(user.getId_user());
  }

  @Transactional
  public User updateProfileUser(Authentication token, DataUpdateProfile dataUpdateProfile) {
    User user = (User) token.getPrincipal();
    User userFounded =  findUserById(user.getId_user());
    userFounded.updateProfile(dataUpdateProfile);
    return userFounded;
  }

  private User findUserById(Long idUser) {
    return userRepository.findById(idUser)
            .orElseThrow(() -> new NotFoundException("User with ID " + idUser + " not found"));
  }


}

package ejercito.demo.service.profile;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.mapper.SoldierMapper;
import ejercito.demo.infra.repository.UserRepository;
import ejercito.demo.models.Assignment;
import ejercito.demo.models.User;
import ejercito.demo.service.assignment.AssignmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProfile {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AssignmentService assignmentService;

  @Autowired
  private SoldierMapper soldierMapper;

  public DataResponseProfile getProfileUser(Authentication token) {
    User userToken = (User) token.getPrincipal();
    User user = findUserById(userToken.getId_user());
    if (user.getSoldier() == null) {
      return new DataResponseProfile(user.getId_user(), user.getUsername(), user.getPassword(), user.getRole(), null, null);
    }
    List<Assignment> assignmentList = assignmentService.getListServicesByIdSoldier(user.getSoldier().getId_soldier());
    return new DataResponseProfile(user.getId_user(), user.getUsername(), user.getPassword(), user.getRole(), user.getSoldier(), soldierMapper.toOrderServicesForStatus(assignmentList));
  }

  @Transactional
  public User updateProfileUser(Authentication token, DataUpdateProfile dataUpdateProfile) {
    validateFields(dataUpdateProfile.currentPassword(), "currentPassword");
    validateFields(dataUpdateProfile.newPassword(), "newPassword");

    User user = (User) token.getPrincipal();
    User userFounded = findUserById(user.getId_user());

    if (!new BCryptPasswordEncoder().matches(dataUpdateProfile.currentPassword(), userFounded.getPassword())) {
      throw new BadRequestException("The password entered is incorrect");
    }

    userFounded.updateProfile(dataUpdateProfile);
    return userFounded;
  }

  private User findUserById(Long idUser) {
    return userRepository.findById(idUser)
            .orElseThrow(() -> new NotFoundException("User with ID " + idUser + " not found"));
  }

  private void validateFields(String field, String fieldName) {
    if (field == null || field.isEmpty()) {
      throw new BadRequestException("Field " + fieldName + " is required");
    }
  }
}

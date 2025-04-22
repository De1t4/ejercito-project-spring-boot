package ejercito.demo.infra.controller;

import ejercito.demo.infra.mapper.SoldierMapper;
import ejercito.demo.infra.repository.UserRepository;
import ejercito.demo.models.Assignment;
import ejercito.demo.models.Soldier;
import ejercito.demo.models.User;
import ejercito.demo.service.assignment.AssignmentService;
import ejercito.demo.service.assignment.dto.response.DataResponseSoldierAssignment;
import ejercito.demo.service.profile.DataResponseProfile;
import ejercito.demo.service.user.DataListUser;
import ejercito.demo.service.user.ServiceUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/v1/users")
@Validated
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ServiceUser serviceUser;

  @GetMapping
  public ResponseEntity<List<DataListUser>> getListUser() {
    return ResponseEntity.ok(createDataUser(userRepository.findAll()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataResponseProfile> getUserProfile(@PathVariable @Positive @NumberFormat Long id){
    return ResponseEntity.ok(serviceUser.getUserProfile(id));
  }

  @DeleteMapping("/delete")
  public ResponseEntity deleteUserList(@RequestBody List<Long> ids_soldier){
    serviceUser.deleteUsersList(ids_soldier);
    return ResponseEntity.noContent().build();
  }

  private List<DataListUser> createDataUser(List<User> users) {
    return users.stream().
            map(user -> new DataListUser(user.getId_user(), user.getUsername(), user.getRole()))
            .collect(Collectors.toList());
  }

}


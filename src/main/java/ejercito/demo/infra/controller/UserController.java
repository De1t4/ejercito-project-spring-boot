package ejercito.demo.infra.controller;

import ejercito.demo.infra.repository.UserRepository;
import ejercito.demo.models.User;
import ejercito.demo.service.user.DataListUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public ResponseEntity<List<DataListUser>> getListUser(){
    return ResponseEntity.ok(createDataUser(userRepository.findAll()));
  }

  private List<DataListUser> createDataUser(List<User> users){
    return users.stream().
              map(user -> new DataListUser(user.getId_user(), user.getUsername(), user.getRole()))
                .collect(Collectors.toList());
  }

}


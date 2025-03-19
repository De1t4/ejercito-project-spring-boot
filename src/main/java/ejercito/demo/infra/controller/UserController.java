package ejercito.demo.infra.controller;


import ejercito.demo.infra.repository.SoldierRepository;
import ejercito.demo.infra.repository.UserRepository;
import ejercito.demo.models.Soldier;
import ejercito.demo.models.User;
import ejercito.demo.service.soldier.ServiceSoldier;
import ejercito.demo.service.user.DataListUser;
import ejercito.demo.service.user.DataRegisterUser;
import ejercito.demo.service.user.ServiceUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ServiceUser serviceUser;

  @GetMapping
  public ResponseEntity<List<DataListUser>> getListUser(){
    return ResponseEntity.status(200).body(createDataUser(userRepository.findAll()));
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody @Valid DataRegisterUser dataRegisterUser, UriComponentsBuilder uriComponentsBuilder) throws Exception {
    User user = serviceUser.createUser(dataRegisterUser);
    URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId_user()).toUri();
    return ResponseEntity.created(url).body(user) ;

  }

  private List<DataListUser> createDataUser(List<User> users){
    return users.stream().
              map(user -> new DataListUser(user.getId_user(), user.getUsername(), user.getRole()))
                .collect(Collectors.toList());
  }

}


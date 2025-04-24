package ejercito.demo.infra.controller;


import ejercito.demo.models.User;
import ejercito.demo.service.auth.DataLoginUser;
import ejercito.demo.service.auth.DataTokenJWT;
import ejercito.demo.service.auth.ServiceAuth;
import ejercito.demo.service.user.DataRegisterUser;
import ejercito.demo.service.user.ServiceUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@Tag(name = "Authentication")
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private ServiceAuth serviceAuth;

  @Autowired
  private ServiceUser serviceUser;

  @PostMapping("/login")
  public ResponseEntity<DataTokenJWT> loginUser(@RequestBody DataLoginUser dataLoginUser){
    return ResponseEntity.ok().body(new DataTokenJWT(serviceAuth.login(dataLoginUser)));
  }

  @PostMapping("/register")
  public ResponseEntity<User> createUser(@RequestBody @Valid DataRegisterUser dataRegisterUser, UriComponentsBuilder uriComponentsBuilder) throws Exception {
    User user = serviceUser.createUser(dataRegisterUser);
    URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId_user()).toUri();
    return ResponseEntity.created(url).body(user) ;
  }

}

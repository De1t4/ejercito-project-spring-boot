package ejercito.demo.infra.controller;

import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.models.User;
import ejercito.demo.service.auth.DataLoginUser;
import ejercito.demo.service.auth.DataTokenJWT;
import ejercito.demo.service.auth.ServiceAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private ServiceAuth serviceAuth;


  @PostMapping("/login")
  public ResponseEntity<DataTokenJWT> loginUser(@RequestBody DataLoginUser dataLoginUser){
    return ResponseEntity.ok().body(new DataTokenJWT(serviceAuth.login(dataLoginUser)));
  }

}

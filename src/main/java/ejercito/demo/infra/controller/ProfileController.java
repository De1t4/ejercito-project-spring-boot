package ejercito.demo.infra.controller;

import ejercito.demo.infra.repository.UserRepository;
import ejercito.demo.models.User;
import ejercito.demo.service.profile.DataUpdateProfile;
import ejercito.demo.service.profile.ServiceProfile;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/v1/profile")
public class ProfileController {

  @Autowired
  private ServiceProfile serviceProfile;

  @GetMapping("/find")
  public ResponseEntity<User> getProfileUser(Authentication token){
    User user = serviceProfile.getProfileUser(token);
    return ResponseEntity.ok(user);
  }

  @PutMapping("/update")
  public ResponseEntity<User> updateProfileUser(Authentication token, @RequestBody DataUpdateProfile dataUpdateProfile){
    User user = serviceProfile.updateProfileUser(token, dataUpdateProfile);
    return ResponseEntity.ok(user);
  }
}

package ejercito.demo.infra.controller;

import ejercito.demo.models.User;
import ejercito.demo.service.profile.DataResponseProfile;
import ejercito.demo.service.profile.DataUpdateProfile;
import ejercito.demo.service.profile.ServiceProfile;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Profile")
@RequestMapping("/v1/profile")
public class ProfileController {

  @Autowired
  private ServiceProfile serviceProfile;

  @GetMapping("/find")
  public ResponseEntity<DataResponseProfile> getProfileUser(Authentication token) {
    DataResponseProfile user = serviceProfile.getProfileUser(token);
    return ResponseEntity.ok(user);
  }

  @PutMapping("/update")
  public ResponseEntity<User> updateProfileUser(Authentication token, @RequestBody DataUpdateProfile dataUpdateProfile) {
    User user = serviceProfile.updateProfileUser(token, dataUpdateProfile);
    return ResponseEntity.ok(user);
  }
}

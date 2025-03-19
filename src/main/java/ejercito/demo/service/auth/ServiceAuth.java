package ejercito.demo.service.auth;

import ejercito.demo.infra.config.TokenService;
import ejercito.demo.infra.errors.BadRequestException;
import ejercito.demo.infra.errors.NotFoundException;
import ejercito.demo.infra.repository.UserRepository;
import ejercito.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceAuth {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TokenService tokenService;

  public String login(DataLoginUser dataLoginUser){
    validateFields(dataLoginUser.username(), "USERNAME");
    validateFields(dataLoginUser.password(), "PASSWORD");
    User user = findUsername(dataLoginUser.username());

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    if (passwordEncoder.matches(dataLoginUser.password(), user.getPassword())) {

      return tokenService.generarToken(user);
    }
    throw new RuntimeException("PASSWORD INVALID");
  }

  private User findUsername(String username) {
    return userRepository.findByUsername(username)
            .orElseThrow(() -> new NotFoundException("User " + username + " not found"));
  }

  private void validateFields(String field, String fieldName) {
    if (field == null || field.isEmpty()) {
      throw new BadRequestException("Field " + fieldName + " is required");
    }
  }
}

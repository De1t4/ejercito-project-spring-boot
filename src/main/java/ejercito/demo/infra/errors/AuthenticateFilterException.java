package ejercito.demo.infra.errors;

import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

public class AuthenticateFilterException extends  RuntimeException{
  public AuthenticateFilterException(String message) {
    super(message);
  }
}

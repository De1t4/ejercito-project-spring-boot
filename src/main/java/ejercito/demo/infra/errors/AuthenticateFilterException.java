package ejercito.demo.infra.errors;

public class AuthenticateFilterException extends RuntimeException {
  public AuthenticateFilterException(String message) {
    super(message);
  }
}

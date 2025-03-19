package ejercito.demo.infra.errors;

public class DuplicateException extends RuntimeException  {
  public DuplicateException(String message) {
    super(message);
  }
}

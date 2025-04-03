package ejercito.demo.infra.errors;

import org.springframework.http.HttpStatus;

public class BussinessException extends RuntimeException{
  private String message;
  private HttpStatus httpStatus;

  public BussinessException(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }


  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}

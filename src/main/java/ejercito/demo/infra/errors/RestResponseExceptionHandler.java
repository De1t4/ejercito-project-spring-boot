package ejercito.demo.infra.errors;

import ejercito.demo.infra.errors.dto.ErrorMessage;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler  {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException exception){
      ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(DuplicateException.class)
  public ResponseEntity<ErrorMessage> handleDuplicateDataException(DuplicateException exception){
    ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FOUND, exception.getMessage());
    return ResponseEntity.status(HttpStatus.FOUND).body(errorMessage);
    }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorMessage> handleBadResquestDataException(BadRequestException expection){
    ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, expection.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
  }

  @ExceptionHandler(AuthenticateFilterException.class)
  public ResponseEntity<ErrorMessage> handleAuthenticateFilter(AuthenticateFilterException exception) {
    ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorMessage> handleAccessDenied(AccessDeniedException exception) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(new ErrorMessage(HttpStatus.FORBIDDEN, "You do not have permission to access this resource"));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorMessage> handleValidatePositiveNumber(ConstraintViolationException exception){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage()));
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorMessage> handleErrorType(MethodArgumentTypeMismatchException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(HttpStatus.BAD_REQUEST, "The variable type entered is incorrect"));
  }
}

package ejercito.demo.infra.errors;

import ejercito.demo.infra.errors.dto.ErrorMessage;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
}

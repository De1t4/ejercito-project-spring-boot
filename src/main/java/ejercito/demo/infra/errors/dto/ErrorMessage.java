package ejercito.demo.infra.errors.dto;

import org.springframework.http.HttpStatus;

public record ErrorMessage(HttpStatus httpStatus, String message) {
}

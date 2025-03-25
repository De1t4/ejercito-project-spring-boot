package ejercito.demo.service.body;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterBody( @NotBlank String denomination) {
}

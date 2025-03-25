package ejercito.demo.service.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DataRegisterService(@NotBlank String description) {
}

package ejercito.demo.service.company;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterCompany(@NotBlank String activity) {
}

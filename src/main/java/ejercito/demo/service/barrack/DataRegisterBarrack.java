package ejercito.demo.service.barrack;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterBarrack(@NotBlank String name, @NotBlank String location) {
}

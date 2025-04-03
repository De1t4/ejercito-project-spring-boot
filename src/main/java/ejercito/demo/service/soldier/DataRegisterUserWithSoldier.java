package ejercito.demo.service.soldier;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterUserWithSoldier(
        @NotBlank
        String username,
        @NotBlank
        String password,
        DataRegisterSoldier soldier
) {

}

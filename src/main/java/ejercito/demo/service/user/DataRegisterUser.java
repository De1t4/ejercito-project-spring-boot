package ejercito.demo.service.user;

import ejercito.demo.service.soldier.DataRegisterSoldier;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;


public record DataRegisterUser(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String role,
        @Valid
        DataRegisterSoldier dataRegisterSoldier

) {

}

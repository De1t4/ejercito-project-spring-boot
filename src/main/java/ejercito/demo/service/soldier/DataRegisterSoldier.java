package ejercito.demo.service.soldier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record DataRegisterSoldier(

        @NotBlank
        String name,
        @NotBlank
        String lastname,
        Date graduation,
        @NotNull
        Long id_company,
        @NotNull
        Long id_barrack,
        @NotNull
        Long id_body
) {
}

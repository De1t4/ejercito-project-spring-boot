package ejercito.demo.service.subOfficial;

import ejercito.demo.models.Soldier;
import ejercito.demo.service.soldier.DataRegisterSoldier;
import jakarta.validation.constraints.NotBlank;

public record DataCreateSubOfficial(
        @NotBlank String username, @NotBlank String password, DataRegisterSoldier soldier
) {
}

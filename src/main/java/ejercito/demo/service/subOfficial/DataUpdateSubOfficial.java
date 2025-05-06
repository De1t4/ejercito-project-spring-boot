package ejercito.demo.service.subOfficial;

import ejercito.demo.service.soldier.DataRegisterSoldier;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record DataUpdateSubOfficial(
        @NotNull Long id_user, String username, DataUpdateSoldierSubOfficial soldier
) {
}

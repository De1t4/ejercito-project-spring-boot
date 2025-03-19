package ejercito.demo.service.soldier;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DataUpdateSoldier(@NotNull Long id_soldier, String name, String lastname, Date graduation) {
}

package ejercito.demo.service.soldier;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record DataUpdateSoldier(DataResponseSoldier dataResponseSoldier, @NotNull Long id_soldier, String name, String lastname, Date graduation) {
}

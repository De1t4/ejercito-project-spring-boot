package ejercito.demo.service.soldier;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record DataUpdateSoldier(@NotNull Long id_soldier, String name, String lastname, Date graduation, Long id_company, Long id_barrack, Long id_body) {
}

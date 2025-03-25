package ejercito.demo.service.soldier;

import java.sql.Date;

public record DataResponseSoldierBasic(Long id_soldier, String name, String lastname, Date graduation) {
}

package ejercito.demo.service.subOfficial;

import java.sql.Date;

public record DataUpdateSoldierSubOfficial(
        String lastname, String name, Date graduation, Long id_barrack, Long id_body, Long id_company
) {
}

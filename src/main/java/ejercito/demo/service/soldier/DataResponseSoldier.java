package ejercito.demo.service.soldier;

import ejercito.demo.models.Barrack;
import ejercito.demo.models.Body;
import ejercito.demo.models.Company;

import java.util.Date;

public record DataResponseSoldier(
        Long id_soldier,
        String name,
        String lastname,
        Date graduation,
        Company company,
        Barrack barrack,
        Body body
        ) {
}

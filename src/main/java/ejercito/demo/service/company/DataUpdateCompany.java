package ejercito.demo.service.company;

import jakarta.validation.constraints.NotNull;

public record DataUpdateCompany(@NotNull Long id_company, String activity) {
}

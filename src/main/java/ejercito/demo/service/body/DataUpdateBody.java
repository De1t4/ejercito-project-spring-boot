package ejercito.demo.service.body;

import jakarta.validation.constraints.NotNull;

public record DataUpdateBody(@NotNull Long id_body, String denomination) {
}

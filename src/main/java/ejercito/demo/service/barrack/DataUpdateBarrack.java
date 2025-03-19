package ejercito.demo.service.barrack;

import jakarta.validation.constraints.NotNull;

public record DataUpdateBarrack(@NotNull Long id_barrack, String name, String location) {
}

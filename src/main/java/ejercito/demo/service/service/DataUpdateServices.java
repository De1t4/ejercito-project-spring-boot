package ejercito.demo.service.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DataUpdateServices(@NotNull Long id_service, String description,
                                  Date end_service,
                                  Long id_soldier) {
}

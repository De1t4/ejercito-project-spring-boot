package ejercito.demo.service.Assignment;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DataFinishAssignment(@NotNull List<Long> id_services_soldiers) {
}

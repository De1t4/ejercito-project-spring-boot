package ejercito.demo.service.assignment.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DataRequestFinishAssignment(@NotNull List<Long> id_services_soldiers) {
}

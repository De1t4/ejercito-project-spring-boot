package ejercito.demo.service.Assignment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DataBothServiceAndAssignment(
        @NotBlank
        String description,
        @NotNull
        List<Long> id_soldier
) {
}

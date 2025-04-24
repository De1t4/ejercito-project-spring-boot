package ejercito.demo.service.assignment.dto.response;

import java.time.LocalDateTime;

public record DataAllServicesAssignment(
        Long id_services_soldiers, String description, Long id_soldier, String soldier,  LocalDateTime at_service, LocalDateTime end_service, Long id_service
) {
}

package ejercito.demo.service.profile;

import java.time.LocalDateTime;

public record DataStatusService(
        Long id_services_soldier, LocalDateTime at_service, LocalDateTime end_service, String description
) {
}

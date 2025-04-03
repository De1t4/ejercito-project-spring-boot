package ejercito.demo.service.assignment.dto.response;

import java.time.LocalDateTime;

public record DataFinishResponseAssignment (Long id_services_soldiers, Long id_soldier, LocalDateTime end_service, LocalDateTime at_service, String description){
}

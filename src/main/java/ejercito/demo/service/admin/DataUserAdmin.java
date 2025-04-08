package ejercito.demo.service.admin;

import java.time.LocalDateTime;

public record DataUserAdmin(Long id_services_soldiers, String name, String description, LocalDateTime days) {
}

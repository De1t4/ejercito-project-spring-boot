package ejercito.demo.service.admin;

import java.util.List;

public record DataGeneralAdmin(
        int total_soldier, int total_services, int services_completed, int services_pending,
        List<DataUserAdmin> recent_services

) {
}

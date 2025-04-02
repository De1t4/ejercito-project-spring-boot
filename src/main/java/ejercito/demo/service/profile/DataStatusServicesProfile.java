package ejercito.demo.service.profile;

import java.util.List;

public record DataStatusServicesProfile(List<DataStatusService> completed, List<DataStatusService> unfinished) {
}

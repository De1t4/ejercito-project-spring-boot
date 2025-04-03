package ejercito.demo.service.profile;

import ejercito.demo.models.Soldier;

public record DataResponseProfile(Long id_user, String username, String password, String role, Soldier soldier, DataStatusServicesProfile services) {
}

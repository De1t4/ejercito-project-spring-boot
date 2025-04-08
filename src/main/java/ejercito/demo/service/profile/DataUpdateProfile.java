package ejercito.demo.service.profile;


import jakarta.validation.constraints.NotBlank;

public record DataUpdateProfile(@NotBlank String currentPassword, @NotBlank String newPassword) {
}

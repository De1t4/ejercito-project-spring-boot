package ejercito.demo.service.auth;

import jakarta.validation.constraints.NotBlank;

public record DataLoginUser(@NotBlank String username, @NotBlank String password) {
}

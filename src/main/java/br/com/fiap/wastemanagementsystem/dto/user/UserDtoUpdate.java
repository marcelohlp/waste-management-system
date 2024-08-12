package br.com.fiap.wastemanagementsystem.dto.user;

import br.com.fiap.wastemanagementsystem.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDtoUpdate(
        @NotNull(message = "Must have an user role!")
        UserRole userRole
) {
}

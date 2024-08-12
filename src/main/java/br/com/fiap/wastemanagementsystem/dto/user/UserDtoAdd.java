package br.com.fiap.wastemanagementsystem.dto.user;

import br.com.fiap.wastemanagementsystem.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.aspectj.weaver.ast.Not;

public record UserDtoAdd(
        @NotNull(message = "Must have a first name!")
        @Size(min = 1, max = 60, message = "The first name must be 1 to 60 characters long!")
        String name,
        @NotNull(message = "Must have an e-mail!")
        @Size(min = 1, max = 120, message = "The e-mail must be 1 to 120 characters long!")
        @Email(message = "Must have a valid e-mail!")
        String email,
        @NotNull(message = "Must have an password!")
        @Size(min = 8, max = 15, message = "The password must be 8 to 15 characters long!")
        String password,
        @NotNull(message = "Must have an user role!")
        UserRole userRole
) {
}

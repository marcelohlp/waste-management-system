package br.com.fiap.wastemanagementsystem.dto.user;

import br.com.fiap.wastemanagementsystem.model.User;
import br.com.fiap.wastemanagementsystem.model.UserRole;

public record UserDtoResponse(
        Long id,
        String name,
        String email,
        UserRole userRole
) {
    public UserDtoResponse(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserRole()
        );
    }
}

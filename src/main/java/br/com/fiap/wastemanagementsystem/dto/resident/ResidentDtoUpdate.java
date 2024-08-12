package br.com.fiap.wastemanagementsystem.dto.resident;

import jakarta.validation.constraints.*;

public record ResidentDtoUpdate(
        @NotNull(message = "Must have an id!")
        Long id,
        @NotBlank(message = "Must have a first name!")
        @Size(min = 1, max = 30, message = "The first name must be 1 to 30 characters long!")
        String firstName,
        @NotBlank(message = "Must have a last name!")
        @Size(min = 1, max = 30, message = "The last name must be 1 to 30 characters long!")
        String lastName,
        @NotNull(message = "Must have a district id!")
        Long districtId
) {
}

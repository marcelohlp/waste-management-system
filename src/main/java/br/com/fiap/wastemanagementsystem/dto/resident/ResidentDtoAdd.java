package br.com.fiap.wastemanagementsystem.dto.resident;

import jakarta.validation.constraints.*;

public record ResidentDtoAdd(
        @NotBlank(message = "Must have a first name!")
        @Size(min = 1, max = 30, message = "The first name must be 1 to 30 characters long!")
        String firstName,
        @NotBlank(message = "Must have a last name!")
        @Size(min = 1, max = 30, message = "The last name must be 1 to 30 characters long!")
        String lastName,
        @NotBlank(message = "Must have an e-mail!")
        @Email(message = "Must have a valid e-mail!")
        @Size(min = 1, max = 120, message = "The e-mail must be 1 to 120 characters long!")
        String email,
        @NotNull(message = "Must have a district id!")
        Long districtId
) {
}

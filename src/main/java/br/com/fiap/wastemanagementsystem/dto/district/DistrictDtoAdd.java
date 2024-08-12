package br.com.fiap.wastemanagementsystem.dto.district;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DistrictDtoAdd(
        @NotBlank(message = "Must have a district name!")
        @Size(min = 1, max = 30, message = "The district name must be 1 to 30 characters long!")
        String name
) {
}

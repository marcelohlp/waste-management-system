package br.com.fiap.wastemanagementsystem.dto.truck;

import br.com.fiap.wastemanagementsystem.model.WasteType;
import jakarta.validation.constraints.*;

public record TruckDtoAdd(

        @NotBlank(message = "Must have a license plate!")
        @Size(min = 7, max = 7, message = "The license plate must have seven characters!")
        String licensePlate,
        @NotNull(message = "Must have a waste type!")
        WasteType wasteType,
        @NotNull(message = "Must have a district id!")
        Long districtId
) {
}

package br.com.fiap.wastemanagementsystem.dto.truck;

import br.com.fiap.wastemanagementsystem.model.WasteType;
import jakarta.validation.constraints.NotNull;

public record TruckDtoUpdate(
        @NotNull(message = "Must have a truck id!")
        Long id,
        @NotNull(message = "Must have a waste type!")
        WasteType wasteType,
        @NotNull(message = "Must have a district id!")
        Long districtId
) {
}

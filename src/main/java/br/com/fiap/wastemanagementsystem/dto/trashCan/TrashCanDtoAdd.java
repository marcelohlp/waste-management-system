package br.com.fiap.wastemanagementsystem.dto.trashCan;

import br.com.fiap.wastemanagementsystem.model.WasteType;
import jakarta.validation.constraints.NotNull;

public record TrashCanDtoAdd(
        @NotNull(message = "Must have a maximum capacity!")
        Double maximumCapacity,
        @NotNull(message = "Must have a waste type!")
        WasteType wasteType,
        @NotNull(message = "Must have a district id!")
        Long districtId
) {
}

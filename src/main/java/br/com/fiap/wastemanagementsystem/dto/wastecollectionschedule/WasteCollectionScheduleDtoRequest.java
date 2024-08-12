package br.com.fiap.wastemanagementsystem.dto.wastecollectionschedule;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record WasteCollectionScheduleDtoRequest(
        @NotNull(message = "Must have a district id!")
        Long districtId,
        @NotNull(message = "Must have an initial date!")
        LocalDate initialDate,
        @NotNull(message = "Must have a final date!")
        LocalDate finalDate
) {

}

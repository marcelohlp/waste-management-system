package br.com.fiap.wastemanagementsystem.dto.resident;

import br.com.fiap.wastemanagementsystem.model.Resident;

public record ResidentDtoResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Long districtId
) {
    public ResidentDtoResponse(Resident resident) {
        this(resident.getId(), resident.getFirstName(), resident.getLastName(), resident.getEmail(), resident.getDistrict().getId());
    }
}

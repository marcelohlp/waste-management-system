package br.com.fiap.wastemanagementsystem.dto.district;

import br.com.fiap.wastemanagementsystem.model.District;

public record DistrictDtoResponse(
        Long id,
        String name
) {
    public DistrictDtoResponse(District district) {
        this(district.getId(), district.getName());
    }
}

package br.com.fiap.wastemanagementsystem.dto.truck;

import br.com.fiap.wastemanagementsystem.model.Truck;
import br.com.fiap.wastemanagementsystem.model.WasteType;

public record TruckDtoResponse(
        Long id,
        String licensePlate,
        WasteType wasteType,
        Long districtId
) {
    public TruckDtoResponse(Truck truck) {
        this(truck.getId(), truck.getLicensePlate(), truck.getWasteType(), truck.getDistrict().getId());
    }
}

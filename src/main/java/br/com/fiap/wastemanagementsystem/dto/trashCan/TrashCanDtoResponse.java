package br.com.fiap.wastemanagementsystem.dto.trashCan;

import br.com.fiap.wastemanagementsystem.model.District;
import br.com.fiap.wastemanagementsystem.model.TrashCan;
import br.com.fiap.wastemanagementsystem.model.WasteType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

public record TrashCanDtoResponse(

        Long id,
        Double maximumCapacity,
        Double usedCapacity,
        WasteType wasteType,
        Long districtId
) {
    public TrashCanDtoResponse(TrashCan trashCan) {
        this(trashCan.getId(), trashCan.getMaximumCapacity(), trashCan.getUsedCapacity(), trashCan.getWasteType(), trashCan.getDistrict().getId());
    }
}

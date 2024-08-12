package br.com.fiap.wastemanagementsystem.dto.wastecollectionschedule;

import br.com.fiap.wastemanagementsystem.model.WasteCollectionSchedule;

import java.time.LocalDate;

public record WasteCollectionScheduleDtoResponse(
        Long id,
        LocalDate scheduledCollectionDate,
        LocalDate collectionDate,
        Boolean finished,
        Long trashCanId,
        Long truckId
) {
    public WasteCollectionScheduleDtoResponse(WasteCollectionSchedule wasteCollectionSchedule) {
        this(
                wasteCollectionSchedule.getId(),
                wasteCollectionSchedule.getScheduledCollectionDate(),
                wasteCollectionSchedule.getCollectionDate(),
                wasteCollectionSchedule.getFinished(),
                wasteCollectionSchedule.getTrashCan().getId(),
                wasteCollectionSchedule.getTruck().getId()
        );
    }
}

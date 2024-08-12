package br.com.fiap.wastemanagementsystem.controller;

import br.com.fiap.wastemanagementsystem.dto.wastecollectionschedule.WasteCollectionScheduleDtoRequest;
import br.com.fiap.wastemanagementsystem.dto.wastecollectionschedule.WasteCollectionScheduleDtoResponse;
import br.com.fiap.wastemanagementsystem.service.WasteCollectionScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class WasteCollectionScheduleController {

    @Autowired
    private WasteCollectionScheduleService wasteCollectionScheduleService;

    @GetMapping("/{id}")
    public ResponseEntity<WasteCollectionScheduleDtoResponse> getWasteCollectionScheduleById(@PathVariable("id") Long id) {
        WasteCollectionScheduleDtoResponse wasteCollectionScheduleDtoResponse =
                wasteCollectionScheduleService.getWasteCollectionScheduleById(id);
        return ResponseEntity.ok(wasteCollectionScheduleDtoResponse);
    }

    @GetMapping("/district/{id}")
    public ResponseEntity<List<WasteCollectionScheduleDtoResponse>> getAllWasteCollectionScheduleByDistrictAndNotFinished(
            @PathVariable("id") Long id
    ) {
        List<WasteCollectionScheduleDtoResponse> wasteCollectionScheduleDtoResponseList =
                wasteCollectionScheduleService.getAllWasteCollectionScheduleByDistrictAndNotFinished(id);
        return ResponseEntity.ok(wasteCollectionScheduleDtoResponseList);
    }

    @GetMapping("/district/period")
    public ResponseEntity<List<WasteCollectionScheduleDtoResponse>> getAllWasteCollectionScheduleByDistrictIdAndByPeriod(
            @RequestBody @Valid WasteCollectionScheduleDtoRequest dto
    ) {
        List<WasteCollectionScheduleDtoResponse> wasteCollectionScheduleDtoResponseList =
                wasteCollectionScheduleService.getAllWasteCollectionScheduleByDistrictIdAndByPeriod(dto);
        return ResponseEntity.ok(wasteCollectionScheduleDtoResponseList);
    }

    @GetMapping("/unfinished/district/period")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<WasteCollectionScheduleDtoResponse>> getAllUnfinishedWasteCollectionScheduleByDistrictByPeriod(
            @RequestBody @Valid WasteCollectionScheduleDtoRequest dto
    ) {
        List<WasteCollectionScheduleDtoResponse> wasteCollectionScheduleDtoResponseList =
                wasteCollectionScheduleService.getAllUnfinishedWasteCollectionScheduleByDistrictByPeriod(dto);
        return ResponseEntity.ok(wasteCollectionScheduleDtoResponseList);
    }

    @GetMapping("/delayed/district/period")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<WasteCollectionScheduleDtoResponse>> getAllDelayedWasteCollectionScheduleByDistrictIdByPeriod(
            @RequestBody @Valid WasteCollectionScheduleDtoRequest dto
    ) {
        List<WasteCollectionScheduleDtoResponse> wasteCollectionScheduleDtoResponseList =
                wasteCollectionScheduleService.getAllDelayedWasteCollectionScheduleByDistrictIdAndByPeriod(dto);
        return ResponseEntity.ok(wasteCollectionScheduleDtoResponseList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<WasteCollectionScheduleDtoResponse> updateScheduleStatus(@PathVariable("id") Long id) {
        WasteCollectionScheduleDtoResponse wasteCollectionScheduleDtoResponse =
                wasteCollectionScheduleService.finishWasteCollectionSchedule(id);
        return ResponseEntity.ok(wasteCollectionScheduleDtoResponse);

    }

}

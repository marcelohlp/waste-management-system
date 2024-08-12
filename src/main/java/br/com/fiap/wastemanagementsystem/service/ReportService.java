package br.com.fiap.wastemanagementsystem.service;

import br.com.fiap.wastemanagementsystem.dto.report.ReportDtoRequest;
import br.com.fiap.wastemanagementsystem.dto.report.ReportDtoResponse;
import br.com.fiap.wastemanagementsystem.model.District;
import br.com.fiap.wastemanagementsystem.model.Report;
import br.com.fiap.wastemanagementsystem.repository.WasteCollectionScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private WasteCollectionScheduleRepository wasteCollectionScheduleRepository;

    @Autowired
    private DistrictService districtService;

    public ReportDtoResponse getReport(ReportDtoRequest dto) {
        District district = districtService.findDistrictById(dto.districtId());
        Integer totalScheduledWasteCollection = getTotalScheduledWasteCollectionByDistrictAndByPeriod(dto);
        Integer totalFinishedWasteCollection = getTotalFinishedWasteCollectionByDistrictAndByPeriod(dto);
        Integer totalDelayedWasteCollection = getTotalDelayedWasteCollectionByDistrictAndByPeriod(dto);
        Report report = new Report(
                district,
                dto.initialDate(),
                dto.finalDate(),
                totalScheduledWasteCollection,
                totalFinishedWasteCollection,
                totalDelayedWasteCollection
        );
        return new ReportDtoResponse(report);
    }

    private Integer getTotalScheduledWasteCollectionByDistrictAndByPeriod(ReportDtoRequest dto) {
        return wasteCollectionScheduleRepository
                .getTotalScheduledWasteCollectionByDistrictAndByPeriod(dto.districtId(), dto.initialDate(), dto.finalDate());
    }

    private Integer getTotalFinishedWasteCollectionByDistrictAndByPeriod(ReportDtoRequest dto) {
        return wasteCollectionScheduleRepository
                .getTotalFinishedWasteCollectionByDistrictAndByPeriod(dto.districtId(), dto.initialDate(), dto.finalDate());
    }

    private Integer getTotalDelayedWasteCollectionByDistrictAndByPeriod(ReportDtoRequest dto) {
        return wasteCollectionScheduleRepository
                .getTotalDelayedWasteCollectionByDistrictAndByPeriod(dto.districtId(), dto.initialDate(), dto.finalDate());
    }

}

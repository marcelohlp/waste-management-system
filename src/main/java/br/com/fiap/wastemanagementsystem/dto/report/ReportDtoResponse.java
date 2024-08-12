package br.com.fiap.wastemanagementsystem.dto.report;

import br.com.fiap.wastemanagementsystem.model.Report;

import java.time.LocalDate;

public record ReportDtoResponse(
        Long districtId,
        LocalDate initialDate,
        LocalDate finaldate,
        Integer totalScheduled,
        Integer totalFinished,
        Integer totalDelayed
) {
    public ReportDtoResponse(Report report) {
        this(
                report.getDistrict().getId(),
                report.getInitialDate(),
                report.getFinalDate(),
                report.getTotalScheduled(),
                report.getTotalFinished(),
                report.getTotalDelayed()
        );
    }
}

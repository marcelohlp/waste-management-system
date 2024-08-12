package br.com.fiap.wastemanagementsystem.controller;

import br.com.fiap.wastemanagementsystem.dto.report.ReportDtoRequest;
import br.com.fiap.wastemanagementsystem.dto.report.ReportDtoResponse;
import br.com.fiap.wastemanagementsystem.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<ReportDtoResponse> getReport(@RequestBody @Valid ReportDtoRequest dto) {
        ReportDtoResponse reportDtoResponse = reportService.getReport(dto);
        return ResponseEntity.ok(reportDtoResponse);
    }

}

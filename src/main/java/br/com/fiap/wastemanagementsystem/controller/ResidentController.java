package br.com.fiap.wastemanagementsystem.controller;

import br.com.fiap.wastemanagementsystem.dto.resident.ResidentDtoAdd;
import br.com.fiap.wastemanagementsystem.dto.resident.ResidentDtoResponse;
import br.com.fiap.wastemanagementsystem.dto.resident.ResidentDtoUpdate;
import br.com.fiap.wastemanagementsystem.service.ResidentService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @GetMapping("/{id}")
    public ResponseEntity<ResidentDtoResponse> getResidentById(@PathVariable Long id) {
        ResidentDtoResponse residentDtoResponse = residentService.getResidentById(id);
        return ResponseEntity.ok(residentDtoResponse);
    }

    @GetMapping
    public ResponseEntity<List<ResidentDtoResponse>> getAllResidents() {
        List<ResidentDtoResponse> residentDtoResponseList = residentService.getAllResidents();
        return ResponseEntity.ok(residentDtoResponseList);
    }

    @GetMapping("/district/{id}")
    public ResponseEntity<List<ResidentDtoResponse>> getAllResidentsByDistrictId(@PathVariable Long id) {
        List<ResidentDtoResponse> residentDtoResponseList = residentService.getAllResidentsByDistrictId(id);
        return ResponseEntity.ok(residentDtoResponseList);
    }

    @PostMapping
    public ResponseEntity<ResidentDtoResponse> addNewResident(@RequestBody @Valid ResidentDtoAdd dto){
        ResidentDtoResponse residentDtoResponse = residentService.addNewResident(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(residentDtoResponse);
    }

    @PutMapping
    public ResponseEntity<ResidentDtoResponse> updateResident(@RequestBody @Valid ResidentDtoUpdate dto) {
        ResidentDtoResponse residentDtoResponse = residentService.updateResident(dto);
        return ResponseEntity.ok(residentDtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeResident(@PathVariable Long id) {
        residentService.removeResident(id);
        return ResponseEntity.noContent().build();
    }

}

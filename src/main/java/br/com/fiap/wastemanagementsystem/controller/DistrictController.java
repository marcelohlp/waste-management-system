package br.com.fiap.wastemanagementsystem.controller;

import br.com.fiap.wastemanagementsystem.dto.district.DistrictDtoAdd;
import br.com.fiap.wastemanagementsystem.dto.district.DistrictDtoResponse;
import br.com.fiap.wastemanagementsystem.dto.district.DistrictDtoUpdate;
import br.com.fiap.wastemanagementsystem.service.DistrictService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/districts")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping("/{id}")
    public ResponseEntity<DistrictDtoResponse> getDistrictById(@PathVariable Long id) {
        DistrictDtoResponse districtDtoResponse = districtService.getDistrictById(id);
        return ResponseEntity.ok(districtDtoResponse);
    }

    @GetMapping
    public ResponseEntity<List<DistrictDtoResponse>> getAllDistricts() {
        List<DistrictDtoResponse> districtDtoResponseList = districtService.getAllDistricts();
        return ResponseEntity.ok(districtDtoResponseList);
    }

    @PostMapping
    public ResponseEntity<DistrictDtoResponse> addNewDistrict(@RequestBody @Valid DistrictDtoAdd dto) {
        DistrictDtoResponse districtDtoResponse = districtService.addNewDistrict(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(districtDtoResponse);
    }

    @PutMapping
    public ResponseEntity<DistrictDtoResponse> updateDistricName(@RequestBody @Valid DistrictDtoUpdate dto) {
        DistrictDtoResponse districtDtoResponse = districtService.updateDistrict(dto);
        return ResponseEntity.ok(districtDtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeDistrict(@PathVariable Long id) {
        districtService.removeDistrict(id);
        return ResponseEntity.noContent().build();
    }

}

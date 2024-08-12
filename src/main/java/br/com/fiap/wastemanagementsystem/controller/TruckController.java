package br.com.fiap.wastemanagementsystem.controller;

import br.com.fiap.wastemanagementsystem.dto.truck.TruckDtoAdd;
import br.com.fiap.wastemanagementsystem.dto.truck.TruckDtoResponse;
import br.com.fiap.wastemanagementsystem.dto.truck.TruckDtoUpdate;
import br.com.fiap.wastemanagementsystem.model.WasteType;
import br.com.fiap.wastemanagementsystem.service.TruckService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @GetMapping("/{id}")
    public ResponseEntity<TruckDtoResponse> getTruckById(@PathVariable("id") Long id) {
        TruckDtoResponse truckDtoResponse = truckService.getTruckById(id);
        return ResponseEntity.ok(truckDtoResponse);
    }

    @GetMapping
    public ResponseEntity<List<TruckDtoResponse>> getAllTrucks() {
        List<TruckDtoResponse> truckDtoResponseList = truckService.getAllTrucks();
        return ResponseEntity.ok(truckDtoResponseList);
    }

    @GetMapping("/district/{id}")
    public ResponseEntity<List<TruckDtoResponse>> getAllTrucksByDistrict(@PathVariable("id") Long id) {
        List<TruckDtoResponse> truckDtoResponseList = truckService.getAllTrucksByDistrict(id);
        return ResponseEntity.ok(truckDtoResponseList);
    }

    @PostMapping
    public ResponseEntity<TruckDtoResponse> addNewTruck(@RequestBody @Valid TruckDtoAdd dto) {
        TruckDtoResponse truckDtoResponse = truckService.addNewTruck(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(truckDtoResponse);
    }

    @PutMapping
    public ResponseEntity<TruckDtoResponse> updateTruck(@RequestBody  @Valid TruckDtoUpdate dto) {
        TruckDtoResponse truckDtoResponse = truckService.updateTruck(dto);
        return ResponseEntity.ok(truckDtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeTrucrk(@PathVariable Long id) {
        truckService.removeTruck(id);
        return ResponseEntity.noContent().build();
    }

}

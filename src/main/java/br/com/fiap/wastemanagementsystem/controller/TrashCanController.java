package br.com.fiap.wastemanagementsystem.controller;

import br.com.fiap.wastemanagementsystem.dto.trashCan.TrashCanDtoAdd;
import br.com.fiap.wastemanagementsystem.dto.trashCan.TrashCanDtoUpdate;
import br.com.fiap.wastemanagementsystem.dto.trashCan.TrashCanDtoResponse;
import br.com.fiap.wastemanagementsystem.service.TrashCanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trash-cans")
public class TrashCanController {

    @Autowired
    private TrashCanService trashCanService;

    @GetMapping("/{id}")
    public ResponseEntity<TrashCanDtoResponse> getTrashCanById(@PathVariable Long id) {
        TrashCanDtoResponse trashCanDtoResponse = trashCanService.getTrashCanById(id);
        return ResponseEntity.ok(trashCanDtoResponse);
    }

    @GetMapping
    public ResponseEntity<List<TrashCanDtoResponse>> getAllTrashCans() {
        List<TrashCanDtoResponse> trashCanDtoResponseList = trashCanService.getAllTrashCans();
        return ResponseEntity.ok(trashCanDtoResponseList);
    }

    @GetMapping("/district/{id}")
    public ResponseEntity<List<TrashCanDtoResponse>> getAllByDistrictId(@PathVariable @Valid Long id) {
        List<TrashCanDtoResponse> trashCanDtoResponseList = trashCanService.getAllByDistrictId(id);
        return ResponseEntity.ok(trashCanDtoResponseList);
    }

    @PostMapping()
    public ResponseEntity<TrashCanDtoResponse> addNewTrashCan(@RequestBody @Valid TrashCanDtoAdd dto) {
        TrashCanDtoResponse trashCanDtoResponse = trashCanService.addNewTrashCan(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(trashCanDtoResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TrashCanDtoResponse> updateUsedCapacity(@PathVariable Long id, @RequestBody @Valid TrashCanDtoUpdate dto) {
        TrashCanDtoResponse trashCanDtoResponse = trashCanService.updateUsedCapacity(id, dto);
        return ResponseEntity.ok(trashCanDtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeTrashCan(@PathVariable Long id) {
        trashCanService.removeTrashCan(id);
        return ResponseEntity.noContent().build();
    }


}

package br.com.fiap.wastemanagementsystem.service;

import br.com.fiap.wastemanagementsystem.dto.truck.TruckDtoAdd;
import br.com.fiap.wastemanagementsystem.dto.truck.TruckDtoResponse;
import br.com.fiap.wastemanagementsystem.dto.truck.TruckDtoUpdate;
import br.com.fiap.wastemanagementsystem.exception.DataNotFoundException;
import br.com.fiap.wastemanagementsystem.model.District;
import br.com.fiap.wastemanagementsystem.model.Truck;
import br.com.fiap.wastemanagementsystem.model.WasteType;
import br.com.fiap.wastemanagementsystem.repository.TruckRepository;
import br.com.fiap.wastemanagementsystem.validation.truck.TruckRegisterValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TruckService {

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private List<TruckRegisterValidation> truckRegisterValidationList;

    public TruckDtoResponse getTruckById(Long id) {
        Truck truck = findTruckById(id);
        return new TruckDtoResponse(truck);
    }

    public List<TruckDtoResponse> getAllTrucks() {
        List<Truck> truckList = truckRepository.findAll();
        return toTruckDtoResponseList(truckList);
    }

    public List<TruckDtoResponse> getAllTrucksByDistrict(Long districtId) {
        District district = districtService.findDistrictById(districtId);
        List<Truck> truckList = truckRepository.findAllByDistrict(district);
        return toTruckDtoResponseList(truckList);
    }

    public TruckDtoResponse addNewTruck(TruckDtoAdd dto) {
        truckRegisterValidationList.forEach(validation -> validation.validate(dto));
        District district = districtService.findDistrictById(dto.districtId());
        Truck truck = new Truck(dto, district);
        truckRepository.save(truck);
        return new TruckDtoResponse(truck);
    }

    public TruckDtoResponse updateTruck(TruckDtoUpdate dto) {
        Truck truck = findTruckById(dto.id());
        District district = districtService.findDistrictById(dto.districtId());
        truck.update(dto, district);
        truckRepository.save(truck);
        return new TruckDtoResponse(truck);
    }

    public void removeTruck(Long id) {
        Truck truck = findTruckById(id);
        truckRepository.delete(truck);
    }

    private Truck findTruckById(Long id) {
        Optional<Truck> truckOptional = truckRepository.findById(id);
        if (truckOptional.isEmpty()) {
            throw new DataNotFoundException("Truck not found by id!");
        }
        return truckOptional.get();
    }

    private List<TruckDtoResponse> toTruckDtoResponseList(List<Truck> list) {
        return list
                .stream()
                .map(TruckDtoResponse::new)
                .toList();
    }

}

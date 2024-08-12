package br.com.fiap.wastemanagementsystem.service;

import br.com.fiap.wastemanagementsystem.dto.resident.ResidentDtoAdd;
import br.com.fiap.wastemanagementsystem.dto.resident.ResidentDtoResponse;
import br.com.fiap.wastemanagementsystem.dto.resident.ResidentDtoUpdate;
import br.com.fiap.wastemanagementsystem.exception.DataNotFoundException;
import br.com.fiap.wastemanagementsystem.model.District;
import br.com.fiap.wastemanagementsystem.model.Resident;
import br.com.fiap.wastemanagementsystem.repository.ResidentRepository;
import br.com.fiap.wastemanagementsystem.validation.resident.ResidentRegisterValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private List<ResidentRegisterValidation> residentRegisterValidationList;

    public ResidentDtoResponse getResidentById(Long id) {
        Resident resident = findResidentById(id);
        return new ResidentDtoResponse(resident);
    }

    public List<ResidentDtoResponse> getAllResidents() {
        List<Resident> residentList = residentRepository.findAll();
        return toResidentDtoResponseList(residentList);
    }

    public List<ResidentDtoResponse> getAllResidentsByDistrictId(Long id) {
        List<Resident> residentList = residentRepository.findAllByDistrictId(id);
        return toResidentDtoResponseList(residentList);
    }

    public ResidentDtoResponse addNewResident(ResidentDtoAdd dto) {
        residentRegisterValidationList.forEach(validation -> validation.validate(dto));
        District district = districtService.findDistrictById(dto.districtId());
        Resident resident = new Resident(dto, district);
        residentRepository.save(resident);
        return new ResidentDtoResponse(resident);
    }

    public ResidentDtoResponse updateResident(ResidentDtoUpdate dto) {
        Resident resident = findResidentById(dto.id());
        District district = districtService.findDistrictById(dto.districtId());
        resident.update(dto, district);
        residentRepository.save(resident);
        return new ResidentDtoResponse(resident);
    }

    public void removeResident(Long id) {
        Resident resident = findResidentById(id);
        residentRepository.delete(resident);
    }

    private Resident findResidentById(Long id) {
        Optional<Resident> residentOptional = residentRepository.findById(id);
        if (residentOptional.isEmpty()) {
            throw new DataNotFoundException("Resident not found by id!");
        }
        return residentOptional.get();
    }

    private List<ResidentDtoResponse> toResidentDtoResponseList(List<Resident> list) {
        return list
                .stream()
                .map(ResidentDtoResponse::new)
                .toList();
    }

}

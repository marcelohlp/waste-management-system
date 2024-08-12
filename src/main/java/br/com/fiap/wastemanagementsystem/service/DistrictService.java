package br.com.fiap.wastemanagementsystem.service;

import br.com.fiap.wastemanagementsystem.dto.district.DistrictDtoAdd;
import br.com.fiap.wastemanagementsystem.dto.district.DistrictDtoResponse;
import br.com.fiap.wastemanagementsystem.dto.district.DistrictDtoUpdate;
import br.com.fiap.wastemanagementsystem.exception.DataNotFoundException;
import br.com.fiap.wastemanagementsystem.model.District;
import br.com.fiap.wastemanagementsystem.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    public DistrictDtoResponse getDistrictById(Long id) {
        District district = findDistrictById(id);
        return new DistrictDtoResponse(district);
    }

    public List<DistrictDtoResponse> getAllDistricts() {
        List<District> districtList = districtRepository.findAll();
        return toDistrictDtoResponseList(districtList);
    }

    public DistrictDtoResponse addNewDistrict(DistrictDtoAdd dto) {
        District district = new District(dto);
        districtRepository.save(district);
        return new DistrictDtoResponse(district);
    }

    public DistrictDtoResponse updateDistrict(DistrictDtoUpdate dto) {
        District district = findDistrictById(dto.id());
        district.update(dto);
        districtRepository.save(district);
        return new DistrictDtoResponse(district);
    }

    public void removeDistrict(Long id) {
        District district = findDistrictById(id);
        districtRepository.delete(district);
    }

    public District findDistrictById(Long id) {
        Optional<District> districtOptional = districtRepository.findById(id);
        if (districtOptional.isEmpty()) {
            throw new DataNotFoundException("District not found by id!");
        }
        return districtOptional.get();
    }

    public void checkIfDistrictExists(Long id) {
        if (!districtRepository.existsById(id)) {
            throw new DataNotFoundException("District does not exist!");
        }
    }

    private List<DistrictDtoResponse> toDistrictDtoResponseList(List<District> list) {
        return list
                .stream()
                .map(DistrictDtoResponse::new)
                .toList();
    }

}

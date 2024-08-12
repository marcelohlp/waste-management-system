package br.com.fiap.wastemanagementsystem.service;

import br.com.fiap.wastemanagementsystem.dto.trashCan.TrashCanDtoAdd;
import br.com.fiap.wastemanagementsystem.dto.trashCan.TrashCanDtoResponse;
import br.com.fiap.wastemanagementsystem.dto.trashCan.TrashCanDtoUpdate;
import br.com.fiap.wastemanagementsystem.exception.DataNotFoundException;
import br.com.fiap.wastemanagementsystem.model.District;
import br.com.fiap.wastemanagementsystem.model.TrashCan;
import br.com.fiap.wastemanagementsystem.repository.TrashCanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrashCanService {

    @Autowired
    private TrashCanRepository trashCanRepository;

    @Autowired
    private DistrictService districtService;

    public TrashCanDtoResponse getTrashCanById(Long id) {
        TrashCan trashCan = findTrashCanById(id);
        return new TrashCanDtoResponse(trashCan);
    }

    public List<TrashCanDtoResponse> getAllTrashCans() {
        List<TrashCan> trashCanList = trashCanRepository.findAll();
        return toTrashCanDtoResponseList(trashCanList);
    }

    public List<TrashCanDtoResponse> getAllByDistrictId(Long id) {
        List<TrashCan> trashCanList = trashCanRepository.findAllByDistrictId(id);
        return toTrashCanDtoResponseList(trashCanList);
    }

    public TrashCanDtoResponse addNewTrashCan(TrashCanDtoAdd dto) {
        District district = districtService.findDistrictById(dto.districtId());
        TrashCan trashCan = new TrashCan(dto, district);
        trashCanRepository.save(trashCan);
        return new TrashCanDtoResponse(trashCan);
    }

    public TrashCanDtoResponse updateUsedCapacity(Long id, TrashCanDtoUpdate dto) {
        TrashCan trashCan = findTrashCanById(id);
        trashCan.addVolumeToUsedCapacity(dto.addedVolume());
        trashCanRepository.save(trashCan);
        return new TrashCanDtoResponse(trashCan);
    }

    public void removeTrashCan(Long id) {
        TrashCan trashCan = findTrashCanById(id);
        trashCanRepository.delete(trashCan);
    }

    private TrashCan findTrashCanById(Long id) {
        Optional<TrashCan> trashCanOptional = trashCanRepository.findById(id);
        if (trashCanOptional.isEmpty()) {
            throw new DataNotFoundException("Trash can not found by id!");
        }
        return trashCanOptional.get();
    }

    private List<TrashCanDtoResponse> toTrashCanDtoResponseList(List<TrashCan> list) {
        return list
                .stream()
                .map(TrashCanDtoResponse::new)
                .toList();
    }

}

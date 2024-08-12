package br.com.fiap.wastemanagementsystem.service;

import br.com.fiap.wastemanagementsystem.dto.wastecollectionschedule.WasteCollectionScheduleDtoRequest;
import br.com.fiap.wastemanagementsystem.dto.wastecollectionschedule.WasteCollectionScheduleDtoResponse;
import br.com.fiap.wastemanagementsystem.exception.DataNotFoundException;
import br.com.fiap.wastemanagementsystem.model.WasteCollectionSchedule;
import br.com.fiap.wastemanagementsystem.repository.WasteCollectionScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WasteCollectionScheduleService {

    @Autowired
    private WasteCollectionScheduleRepository wasteCollectionScheduleRepository;

    @Autowired
    private DistrictService districtService;

    public WasteCollectionScheduleDtoResponse getWasteCollectionScheduleById(Long id) {
        WasteCollectionSchedule wasteCollectionSchedule = findWasteCollectionScheduleById(id);
        return new WasteCollectionScheduleDtoResponse(wasteCollectionSchedule);
    }

    public List<WasteCollectionScheduleDtoResponse> getAllWasteCollectionScheduleByDistrictAndNotFinished(Long id) {
        districtService.checkIfDistrictExists(id);
        List<WasteCollectionSchedule> wasteCollectionScheduleList =
                wasteCollectionScheduleRepository.findAllWasteCollectionScheduleByDistrictAndNotFinished(id);
        return toWasteCollectionScheduleDtoResponsesList(wasteCollectionScheduleList);
    }

    public List<WasteCollectionScheduleDtoResponse> getAllWasteCollectionScheduleByDistrictIdAndByPeriod(WasteCollectionScheduleDtoRequest dto) {
        districtService.checkIfDistrictExists(dto.districtId());
        List<WasteCollectionSchedule> wasteCollectionScheduleList =
                wasteCollectionScheduleRepository
                        .findAllByDistrictAndByPeriod(
                                dto.districtId(),
                                dto.initialDate(),
                                dto.finalDate()
                        );
        return toWasteCollectionScheduleDtoResponsesList(wasteCollectionScheduleList);
    }
    public List<WasteCollectionScheduleDtoResponse> getAllUnfinishedWasteCollectionScheduleByDistrictByPeriod(WasteCollectionScheduleDtoRequest dto) {
        districtService.checkIfDistrictExists(dto.districtId());
        List<WasteCollectionSchedule> wasteCollectionScheduleList =
                wasteCollectionScheduleRepository
                        .findAllUnfinishedWasteCollectionByDistrictAndByPeriod(
                                dto.districtId(),
                                dto.initialDate(),
                                dto.finalDate()
                        );
        return toWasteCollectionScheduleDtoResponsesList(wasteCollectionScheduleList);
    }

    public List<WasteCollectionScheduleDtoResponse> getAllDelayedWasteCollectionScheduleByDistrictIdAndByPeriod(WasteCollectionScheduleDtoRequest dto) {
        districtService.checkIfDistrictExists(dto.districtId());
        List<WasteCollectionSchedule> wasteCollectionScheduleList =
                wasteCollectionScheduleRepository
                        .findAllDelayedWasteCollectionByDistrictIdAndByPeriod(
                                dto.districtId(),
                                LocalDate.now(),
                                dto.initialDate(),
                                dto.finalDate()
                        );
        return toWasteCollectionScheduleDtoResponsesList(wasteCollectionScheduleList);
    }

    public WasteCollectionScheduleDtoResponse finishWasteCollectionSchedule(Long id) {
        WasteCollectionSchedule wasteCollectionSchedule = findWasteCollectionScheduleById(id);
        wasteCollectionSchedule.finish();
        wasteCollectionScheduleRepository.save(wasteCollectionSchedule);
        return new WasteCollectionScheduleDtoResponse(wasteCollectionSchedule);
    }

    private WasteCollectionSchedule findWasteCollectionScheduleById(Long id) {
        Optional<WasteCollectionSchedule> wasteCollectionScheduleOptional =
                wasteCollectionScheduleRepository.findById(id);
        if (wasteCollectionScheduleOptional.isEmpty()) {
            throw new DataNotFoundException("Waste collection schedule not found by id!");
        }
        return wasteCollectionScheduleOptional.get();
    }

    private List<WasteCollectionScheduleDtoResponse> toWasteCollectionScheduleDtoResponsesList(List<WasteCollectionSchedule> list) {
        return list
                .stream()
                .map(WasteCollectionScheduleDtoResponse::new)
                .toList();
    }

}

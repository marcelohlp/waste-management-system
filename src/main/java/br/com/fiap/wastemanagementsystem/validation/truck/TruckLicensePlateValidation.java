package br.com.fiap.wastemanagementsystem.validation.truck;

import br.com.fiap.wastemanagementsystem.dto.truck.TruckDtoAdd;
import br.com.fiap.wastemanagementsystem.exception.InvalidDataException;
import br.com.fiap.wastemanagementsystem.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TruckLicensePlateValidation implements TruckRegisterValidation{

    @Autowired
    private TruckRepository truckRepository;

    @Override
    public void validate(TruckDtoAdd dto) {
        if(truckRepository.existsByLicensePlate(dto.licensePlate())) {
            throw new InvalidDataException("There is already a truck registered with this license plate!");
        }
    }
}

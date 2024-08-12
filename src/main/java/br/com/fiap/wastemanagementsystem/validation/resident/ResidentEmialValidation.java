package br.com.fiap.wastemanagementsystem.validation.resident;

import br.com.fiap.wastemanagementsystem.dto.resident.ResidentDtoAdd;
import br.com.fiap.wastemanagementsystem.exception.InvalidDataException;
import br.com.fiap.wastemanagementsystem.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResidentEmialValidation implements ResidentRegisterValidation {

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public void validate(ResidentDtoAdd dto) {
        if (residentRepository.existsByEmail(dto.email())) {
            throw new InvalidDataException("There is already a resident registered with this email!");
        }
    }

}

package br.com.fiap.wastemanagementsystem.validation.resident;

import br.com.fiap.wastemanagementsystem.dto.resident.ResidentDtoAdd;

public interface ResidentRegisterValidation {

    void validate(ResidentDtoAdd dto);

}

package br.com.fiap.wastemanagementsystem.validation.user;

import br.com.fiap.wastemanagementsystem.dto.user.UserDtoAdd;

public interface UserRegisterValidation {

    void validate(UserDtoAdd dto);

}

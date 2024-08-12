package br.com.fiap.wastemanagementsystem.validation.user;

import br.com.fiap.wastemanagementsystem.dto.user.UserDtoAdd;
import br.com.fiap.wastemanagementsystem.exception.InvalidDataException;
import br.com.fiap.wastemanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEmailValidation implements UserRegisterValidation {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(UserDtoAdd dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new InvalidDataException("There is already a user registered with this email!");
        }
    }
}

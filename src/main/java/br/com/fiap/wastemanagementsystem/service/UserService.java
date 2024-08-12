package br.com.fiap.wastemanagementsystem.service;

import br.com.fiap.wastemanagementsystem.dto.user.UserDtoAdd;
import br.com.fiap.wastemanagementsystem.dto.user.UserDtoResponse;
import br.com.fiap.wastemanagementsystem.dto.user.UserDtoUpdate;
import br.com.fiap.wastemanagementsystem.exception.DataNotFoundException;
import br.com.fiap.wastemanagementsystem.model.User;
import br.com.fiap.wastemanagementsystem.repository.UserRepository;
import br.com.fiap.wastemanagementsystem.validation.user.UserRegisterValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private List<UserRegisterValidation> userRegisterValidationList;

    public UserDtoResponse getUserById(Long id) {
        User user = findUserById(id);
        return new UserDtoResponse(user);
    }

    public List<UserDtoResponse> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return toUserDtoResponseList(userList);
    }

    public UserDtoResponse addUser(UserDtoAdd dto) {
        userRegisterValidationList.forEach(validation -> validation.validate(dto));
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User user = new User(dto);
        user.setToEncryptedPassword(encryptedPassword);
        userRepository.save(user);
        return new UserDtoResponse(user);
    }

    public UserDtoResponse updateUserRole(Long id, UserDtoUpdate dto) {
        User user = findUserById(id);
        user.updateRole(dto.userRole());
        userRepository.save(user);
        return new UserDtoResponse(user);
    }

    public void removeUser(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    private User findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new DataNotFoundException("User not found by id!");
        }
        return userOptional.get();
    }

    private List<UserDtoResponse> toUserDtoResponseList(List<User> list) {
        return list
                .stream()
                .map(UserDtoResponse::new)
                .toList();
    }
}

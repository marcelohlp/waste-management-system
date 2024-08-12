package br.com.fiap.wastemanagementsystem.controller;

import br.com.fiap.wastemanagementsystem.dto.user.UserDtoResponse;
import br.com.fiap.wastemanagementsystem.dto.user.UserDtoUpdate;
import br.com.fiap.wastemanagementsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable("id") Long id) {
        UserDtoResponse userDtoResponse = userService.getUserById(id);
        return ResponseEntity.ok(userDtoResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> getAllUsers() {
        List<UserDtoResponse> userDtoResponseList = userService.getAllUsers();
        return ResponseEntity.ok(userDtoResponseList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDtoResponse> updateUserRole(@PathVariable("id") Long id, @RequestBody @Valid UserDtoUpdate dto) {
        UserDtoResponse userDtoResponse = userService.updateUserRole(id, dto);
        return ResponseEntity.ok(userDtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    }


}

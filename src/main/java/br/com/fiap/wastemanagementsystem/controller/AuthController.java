package br.com.fiap.wastemanagementsystem.controller;

import br.com.fiap.wastemanagementsystem.config.security.TokenService;
import br.com.fiap.wastemanagementsystem.dto.user.UserDtoAdd;
import br.com.fiap.wastemanagementsystem.dto.user.UserDtoLogin;
import br.com.fiap.wastemanagementsystem.dto.user.UserDtoResponse;
import br.com.fiap.wastemanagementsystem.dto.user.UserDtoToken;
import br.com.fiap.wastemanagementsystem.model.User;
import br.com.fiap.wastemanagementsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<UserDtoToken> login(@RequestBody @Valid UserDtoLogin dto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenService.getToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new UserDtoToken(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDtoResponse> register(@RequestBody @Valid UserDtoAdd dto) {
        UserDtoResponse userDtoResponse = userService.addUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoResponse);
    }

}

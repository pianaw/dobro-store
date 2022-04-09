package ru.kpfu.ds.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.ds.mainservice.model.dto.AuthTokenDTO;
import ru.kpfu.ds.mainservice.model.dto.UserDTO;
import ru.kpfu.ds.mainservice.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public AuthTokenDTO authenticate(@RequestBody UserDTO dto) {
        return authService.authenticate(dto);
    }

    @PostMapping("/refresh")
    public AuthTokenDTO refresh() {
        return authService.refreshToken();
    }
}

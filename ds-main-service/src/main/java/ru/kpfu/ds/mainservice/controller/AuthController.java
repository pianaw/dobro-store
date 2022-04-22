package ru.kpfu.ds.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.ds.mainservice.model.dto.AuthTokenDTO;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.dto.UserEmailPasswordDTO;
import ru.kpfu.ds.mainservice.service.AuthService;
import ru.kpfu.ds.mainservice.service.TokenService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authServiceImpl;
    private final TokenService jwtService;

    @PostMapping
    public AuthTokenDTO authenticate(@RequestBody UserEmailPasswordDTO dto) {
        return authServiceImpl.authenticate(dto);
    }

    @PostMapping("/refresh")
    public AuthTokenDTO refresh(@AuthenticationPrincipal CurrentUserDTO currentUser) {
        return jwtService.refreshToken(currentUser);
    }
}

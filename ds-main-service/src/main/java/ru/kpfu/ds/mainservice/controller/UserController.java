package ru.kpfu.ds.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final AuthService authServiceImpl;

    @GetMapping("/current")
    public CurrentUserDTO getCurrentUserInfo() {
        return authServiceImpl.getCurrentUser();
    }
}

package ru.kpfu.ds.mainservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.ds.mainservice.model.dto.JwtTokenDTO;
import ru.kpfu.ds.mainservice.model.dto.UserDTO;
import ru.kpfu.ds.mainservice.model.entity.User;
import ru.kpfu.ds.mainservice.model.exception.PasswordMismatchException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;
    private final JwtService jwtService;

    public JwtTokenDTO authenticate(UserDTO dto) {
        User user = userService.getByEmailOrThrow(dto.getEmail());

        if (!passwordEncoder.matches(dto.getPassword(), user.getHashPassword())) {
            throw new PasswordMismatchException("Invalid password");
        }

        UUID jti = UUID.randomUUID();
        redisService.saveUserJTI(jti, user);

        return jwtService.createJwtToken(user, jti);
    }
}
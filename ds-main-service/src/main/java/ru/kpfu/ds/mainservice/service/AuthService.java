package ru.kpfu.ds.mainservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.dto.AuthTokenDTO;
import ru.kpfu.ds.mainservice.model.dto.UserDTO;
import ru.kpfu.ds.mainservice.model.entity.User;
import ru.kpfu.ds.mainservice.model.exception.PasswordMismatchException;
import ru.kpfu.ds.mainservice.model.exception.UnknownException;
import ru.kpfu.ds.mainservice.security.JwtAuthentication;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;
    private final JwtService jwtService;

    public CurrentUserDTO getCurrentUser() {
        Authentication authentication = getAuthentication();
        if (authentication instanceof JwtAuthentication) {
            return (CurrentUserDTO) authentication.getPrincipal();
        } else {
            throw new UnknownException("Unknown exception during user identification");
        }
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public AuthTokenDTO authenticate(UserDTO dto) {
        User user = userService.getByEmailOrThrow(dto.getEmail());

        if (!passwordEncoder.matches(dto.getPassword(), user.getHashPassword())) {
            throw new PasswordMismatchException("Invalid password");
        }

        return createTokenDTO(user);
    }

    private AuthTokenDTO createTokenDTO(User user) {
        UUID jti = UUID.randomUUID();
        redisService.saveUserJTI(jti, user);

        return jwtService.createJwtToken(user, jti);
    }

    public AuthTokenDTO refreshToken() {
        CurrentUserDTO currentUserDTO = getCurrentUser();
        User user = userService.getByRoleAndOwnerIdOrThrow(currentUserDTO.getRole(), currentUserDTO.getOwnerId());

        return createTokenDTO(user);
    }
}
package ru.kpfu.ds.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.ds.mainservice.model.dto.AuthTokenDTO;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.dto.UserEmailPasswordDTO;
import ru.kpfu.ds.mainservice.model.entity.User;
import ru.kpfu.ds.mainservice.model.exception.PasswordMismatchException;
import ru.kpfu.ds.mainservice.model.exception.UnknownException;
import ru.kpfu.ds.mainservice.security.JwtAuthentication;
import ru.kpfu.ds.mainservice.service.AuthService;
import ru.kpfu.ds.mainservice.service.TokenService;
import ru.kpfu.ds.mainservice.util.bcrypt.BCryptUtil;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final TokenService tokenService;
    private final BCryptUtil bCryptUtil;

    @Override
    public AuthTokenDTO authenticate(UserEmailPasswordDTO dto) {
        User user = userService.getByEmailOrThrow(dto.getEmail());

        if (!bCryptUtil.match(dto.getPassword(), user.getHashPassword())) {
            throw new PasswordMismatchException("Invalid password");
        }

        return tokenService.createToken(user);
    }

    @Override
    public CurrentUserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthentication) {
            return (CurrentUserDTO) authentication.getPrincipal();
        } else {
            throw new UnknownException("Unknown exception during user identification");
        }
    }
}
package ru.kpfu.ds.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.ds.mainservice.model.dto.AuthTokenDTO;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.entity.User;
import ru.kpfu.ds.mainservice.service.TokenService;
import ru.kpfu.ds.mainservice.util.jwt.creator.JwtCreator;
import ru.kpfu.ds.mainservice.util.jwt.creator.JwtCreatorFactory;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JWTService implements TokenService {

    private final JwtCreatorFactory factory;
    private final RedisUserService redisUserService;
    private final UserService userService;

    @Override
    public AuthTokenDTO createToken(User user) {
        UUID jti = UUID.randomUUID();
        redisUserService.saveUserJTI(jti, user);

        return createJwtToken(user, jti);
    }

    @Override
    public AuthTokenDTO refreshToken(CurrentUserDTO currentUserDTO) {
        User user = userService.getByRoleAndOwnerIdOrThrow(currentUserDTO.getRole(), currentUserDTO.getOwnerId());

        return createToken(user);
    }

    private AuthTokenDTO createJwtToken(User user, UUID jti) {
        JwtCreator jwtCreator = factory.getJwtCreatorByUserRole(user.getUserRole());

        String accessToken = jwtCreator.createAccessTokenFor(user, jti);
        String refreshToken = jwtCreator.createRefreshTokenFor(user, jti);

        return new AuthTokenDTO(accessToken, refreshToken);
    }
}

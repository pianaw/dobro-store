package ru.kpfu.ds.mainservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.ds.mainservice.model.dto.JwtTokenDTO;
import ru.kpfu.ds.mainservice.model.entity.User;
import ru.kpfu.ds.mainservice.util.jwt.creator.JwtCreator;
import ru.kpfu.ds.mainservice.util.jwt.creator.JwtCreatorFactory;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtCreatorFactory factory;

    public JwtTokenDTO createJwtToken(User user, UUID jti) {
        JwtCreator jwtCreator = factory.getJwtCreatorByUserRole(user.getUserRole());

        String accessToken = jwtCreator.createAccessTokenFor(user);
        String refreshToken = jwtCreator.createRefreshTokenFor(user, jti);

        return new JwtTokenDTO(accessToken, refreshToken);
    }
}

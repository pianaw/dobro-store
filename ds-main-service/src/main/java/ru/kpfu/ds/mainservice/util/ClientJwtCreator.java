package ru.kpfu.ds.mainservice.util;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class ClientJwtCreator extends JwtCreator {

    private final Algorithm clientAlgorithm;

    @Value("${ds.app.auth.jwt.client.access-token-expiration}")
    private long clientAccessTokenExpiration;

    @Value("${ds.app.auth.jwt.client.refresh-token-expiration}")
    private long clientRefreshTokenExpiration;

    @Override
    protected Date getAccessTokenExpirationTime() {
        return getExpirationDate(clientAccessTokenExpiration);
    }

    @Override
    protected Date getRefreshTokenExpirationTime() {
        return getExpirationDate(clientRefreshTokenExpiration);
    }

    @Override
    protected String sign(JWTCreator.Builder builder) {
        return builder.sign(clientAlgorithm);
    }

    @Override
    public UserRole getUserRole() {
        return UserRole.CLIENT;
    }
}

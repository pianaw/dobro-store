package ru.kpfu.ds.mainservice.util.jwt.creator;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class AdminJwtCreator extends JwtCreator {

    private final Algorithm adminAlgorithm;

    @Value("${ds.app.auth.jwt.admin.access-token-expiration}")
    private long adminAccessTokenExpiration;

    @Value("${ds.app.auth.jwt.admin.refresh-token-expiration}")
    private long adminRefreshTokenExpiration;

    @Override
    protected Date getAccessTokenExpirationTime() {
        return getExpirationDate(adminAccessTokenExpiration);
    }

    @Override
    protected Date getRefreshTokenExpirationTime() {
        return getExpirationDate(adminRefreshTokenExpiration);
    }

    @Override
    protected String sign(JWTCreator.Builder builder) {
        return builder.sign(adminAlgorithm);
    }

    @Override
    public UserRole getUserRole() {
        return UserRole.ADMIN;
    }
}

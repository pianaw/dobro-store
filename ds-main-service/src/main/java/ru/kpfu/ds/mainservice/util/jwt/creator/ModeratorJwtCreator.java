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
public class ModeratorJwtCreator extends JwtCreator {

    private final Algorithm moderatorAlgorithm;

    @Value("${ds.app.auth.jwt.moderator.access-token-expiration}")
    private long moderatorAccessTokenExpiration;

    @Value("${ds.app.auth.jwt.moderator.refresh-token-expiration}")
    private long moderatorRefreshTokenExpiration;

    @Override
    protected Date getAccessTokenExpirationTime() {
        return getExpirationDate(moderatorAccessTokenExpiration);
    }

    @Override
    protected Date getRefreshTokenExpirationTime() {
        return getExpirationDate(moderatorRefreshTokenExpiration);
    }

    @Override
    protected String sign(JWTCreator.Builder builder) {
        return builder.sign(moderatorAlgorithm);
    }

    @Override
    public UserRole getUserRole() {
        return UserRole.MODERATOR;
    }
}

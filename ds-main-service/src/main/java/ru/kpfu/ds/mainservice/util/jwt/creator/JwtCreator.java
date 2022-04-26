package ru.kpfu.ds.mainservice.util.jwt.creator;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import org.springframework.stereotype.Component;
import ru.kpfu.ds.mainservice.model.entity.User;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public abstract class JwtCreator {

    public String createAccessTokenFor(User user, UUID jti) {
        JWTCreator.Builder builder = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("role", user.getRole().toString())
                .withClaim("email", user.getEmail())
                .withClaim("redisId", user.getRedisId().toString())
                .withClaim("ownerId", user.getOwnerId())
                .withJWTId(jti.toString());

        Date expirationDate = getAccessTokenExpirationTime();
        builder.withExpiresAt(expirationDate);

        return sign(builder);
    }

    public String createRefreshTokenFor(User user, UUID jti) {
        JWTCreator.Builder builder = JWT.create()
                .withSubject(user.getOwnerId().toString())
                .withClaim("role", getUserRole().toString())
                .withJWTId(jti.toString());

        Date expirationDate = getRefreshTokenExpirationTime();
        builder.withExpiresAt(expirationDate);

        return sign(builder);
    }

    protected Date getExpirationDate(Long timeToLive) {
        return Date.from(Instant.now().plusSeconds(timeToLive));
    }

    protected abstract Date getAccessTokenExpirationTime();

    protected abstract Date getRefreshTokenExpirationTime();

    protected abstract String sign(JWTCreator.Builder builder);

    public abstract UserRole getUserRole();
}
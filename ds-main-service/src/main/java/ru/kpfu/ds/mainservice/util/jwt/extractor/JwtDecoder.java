package ru.kpfu.ds.mainservice.util.jwt.extractor;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.enums.TokenType;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

import java.util.UUID;

@Component
public abstract class JwtDecoder {

    public CurrentUserDTO verifyAndDecode(String jwt, TokenType tokenType) {
        if (TokenType.ACCESS_TOKEN.equals(tokenType)) {
            return verifyAndDecodeAccessToken(jwt);
        } else if (TokenType.REFRESH_TOKEN.equals(tokenType)) {
            return verifyAndDecodeRefreshToken(jwt);
        }
        throw new IllegalArgumentException("Not supported token type for claims extraction");
    }

    private CurrentUserDTO verifyAndDecodeAccessToken(String jwt) {
        DecodedJWT decodedJWT = verify(jwt);

        String email = decodedJWT.getClaim("email").asString();
        UUID redisId = UUID.fromString(decodedJWT.getClaim("redisId").asString());
        Long ownerId = decodedJWT.getClaim("ownerId").asLong();
        UUID jti = UUID.fromString(decodedJWT.getId());

        return CurrentUserDTO.builder()
                .role(getUserRole())
                .email(email)
                .redisId(redisId)
                .ownerId(ownerId)
                .jti(jti)
                .build();
    }

    private CurrentUserDTO verifyAndDecodeRefreshToken(String jwt) {
        DecodedJWT decodedJWT = verify(jwt);

        return CurrentUserDTO.builder()
                .ownerId(Long.parseLong(decodedJWT.getSubject()))
                .jti(UUID.fromString(decodedJWT.getId()))
                .role(getUserRole())
                .build();
    }

    protected abstract DecodedJWT verify(String jwt);

    public abstract UserRole getUserRole();

}

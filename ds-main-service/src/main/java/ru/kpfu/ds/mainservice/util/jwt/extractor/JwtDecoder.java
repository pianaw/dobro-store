package ru.kpfu.ds.mainservice.util.jwt.extractor;

import com.auth0.jwt.interfaces.DecodedJWT;
import liquibase.pro.packaged.C;
import org.springframework.beans.propertyeditors.CurrencyEditor;
import org.springframework.stereotype.Component;
import ru.kpfu.ds.mainservice.model.constant.Constant;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.enums.TokenType;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

import java.util.UUID;
import java.util.function.Consumer;

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
        CurrentUserDTO currentUserDTO = build(decodedJWT);

        String email = decodedJWT.getClaim("email").asString();
        UUID redisId = UUID.fromString(decodedJWT.getClaim("redisId").asString());
        Long ownerId = decodedJWT.getClaim("ownerId").asLong();
        UUID jti = UUID.fromString(decodedJWT.getId());

        currentUserDTO.setEmail(email);
        currentUserDTO.setRedisId(redisId);
        currentUserDTO.setOwnerId(ownerId);
        currentUserDTO.setRole(getUserRole());
        currentUserDTO.setJti(jti);

        return currentUserDTO;
    }

    private CurrentUserDTO verifyAndDecodeRefreshToken(String jwt) {
        DecodedJWT decodedJWT = verify(jwt);
        CurrentUserDTO currentUserDTO = new CurrentUserDTO();
        currentUserDTO.setRole(getUserRole());
        currentUserDTO.setOwnerId(Long.parseLong(decodedJWT.getSubject()));
        currentUserDTO.setJti(UUID.fromString(decodedJWT.getId()));

        return currentUserDTO;
    }

    protected abstract DecodedJWT verify(String jwt);

    protected abstract CurrentUserDTO build(DecodedJWT jwt);

    public abstract UserRole getUserRole();

}

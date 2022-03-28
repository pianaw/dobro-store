package ru.kpfu.ds.mainservice.util.jwt.extractor;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

import java.util.UUID;

@Component
public abstract class JwtDecoder {

    public CurrentUserDTO verifyAndDecode(String jwt) {
        DecodedJWT decodedJWT = verify(jwt);
        CurrentUserDTO currentUserDTO = build(decodedJWT);

        String email = decodedJWT.getClaim("email").asString();
        UUID redisId = UUID.fromString(decodedJWT.getClaim("redisId").asString());
        Long ownerId = decodedJWT.getClaim("ownerId").asLong();

        currentUserDTO.setEmail(email);
        currentUserDTO.setRedisId(redisId);
        currentUserDTO.setOwnerId(ownerId);
        currentUserDTO.setRole(getUserRole());

        return currentUserDTO;
    }

    protected abstract DecodedJWT verify(String jwt);

    protected abstract CurrentUserDTO build(DecodedJWT jwt);

    public abstract UserRole getUserRole();

}

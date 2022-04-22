package ru.kpfu.ds.mainservice.util.jwt.extractor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

@Component
@RequiredArgsConstructor
public class ModeratorJwtDecoder extends JwtDecoder {

    private final Algorithm moderatorAlgorithm;

    @Override
    protected DecodedJWT verify(String jwt) {
        return JWT.require(moderatorAlgorithm).build().verify(jwt);
    }

    @Override
    public UserRole getUserRole() {
        return UserRole.MODERATOR;
    }
}

package ru.kpfu.ds.mainservice.util.jwt.extractor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

@Component
@RequiredArgsConstructor
public class ClientJwtDecoder extends JwtDecoder {

    private final Algorithm clientAlgorithm;

    @Override
    protected DecodedJWT verify(String jwt) {
        return JWT.require(clientAlgorithm).build().verify(jwt);
    }

    @Override
    public UserRole getUserRole() {
        return UserRole.CLIENT;
    }
}

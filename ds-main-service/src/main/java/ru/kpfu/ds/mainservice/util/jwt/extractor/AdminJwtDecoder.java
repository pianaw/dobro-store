package ru.kpfu.ds.mainservice.util.jwt.extractor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kpfu.ds.mainservice.model.dto.CurrentAdminDTO;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

@Component
@RequiredArgsConstructor
public class AdminJwtDecoder extends JwtDecoder {

    private final Algorithm adminAlgorithm;

    @Override
    protected DecodedJWT verify(String jwt) {
        return JWT.require(adminAlgorithm).build().verify(jwt);
    }

    @Override
    protected CurrentUserDTO build(DecodedJWT jwt) {
        return CurrentAdminDTO.builder()
                .build();
    }

    @Override
    public UserRole getUserRole() {
        return UserRole.ADMIN;
    }
}

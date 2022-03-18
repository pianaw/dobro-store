package ru.kpfu.ds.mainservice.util;

import org.springframework.stereotype.Component;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtCreatorFactory {

    private final Map<UserRole, JwtCreator> userRoleJwtCreatorMap;

    public JwtCreatorFactory(List<JwtCreator> jwtCreators) {
        userRoleJwtCreatorMap = jwtCreators.stream()
                .collect(Collectors.toMap(JwtCreator::getUserRole, Function.identity()));
    }

    public JwtCreator getJwtCreatorByUserRole(UserRole userRole) {
        return userRoleJwtCreatorMap.get(userRole);
    }
}

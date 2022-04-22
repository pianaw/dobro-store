package ru.kpfu.ds.mainservice.service;

import ru.kpfu.ds.mainservice.model.dto.AuthTokenDTO;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.entity.User;

public interface TokenService {

    AuthTokenDTO createToken(User user);
    AuthTokenDTO refreshToken(CurrentUserDTO currentUser);
}

package ru.kpfu.ds.mainservice.service;

import ru.kpfu.ds.mainservice.model.dto.AuthTokenDTO;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.dto.UserEmailPasswordDTO;

public interface AuthService {

    AuthTokenDTO authenticate(UserEmailPasswordDTO dto);

    CurrentUserDTO getCurrentUser();
}

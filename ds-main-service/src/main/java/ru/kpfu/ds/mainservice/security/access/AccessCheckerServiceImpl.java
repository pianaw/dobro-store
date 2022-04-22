package ru.kpfu.ds.mainservice.security.access;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.enums.UserRole;
import ru.kpfu.ds.mainservice.service.AuthService;

@Service("accessChecker")
@RequiredArgsConstructor
public class AccessCheckerServiceImpl implements AccessCheckerService {

    private final AuthService authServiceImpl;

    // TODO: добавить возможность проверки разрешения клиента на просмотр статистики и основной информации
    //  + обработка ошибок доступа

    @Override
    public boolean isClientMainInfoVisible(Long clientId) {
        return isCurrentClient(clientId);
    }

    @Override
    public boolean isClientStatisticVisible(Long clientId) {
        return isCurrentClient(clientId);
    }

    private boolean isCurrentClient(Long clientId) {
        CurrentUserDTO currentUser = authServiceImpl.getCurrentUser();

        return UserRole.CLIENT.equals(currentUser.getRole())
                && clientId.equals(currentUser.getOwnerId());
    }
}

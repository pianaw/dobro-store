package ru.kpfu.ds.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.ds.mainservice.model.dto.BaseClientDTO;
import ru.kpfu.ds.mainservice.model.dto.FullClientDTO;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserService userService;
    private final ClientService clientService;

    @Transactional
    public BaseClientDTO signUp(FullClientDTO form) {
        FullClientDTO clientDTO = clientService.add(form);
        userService.add(clientDTO.getUser());
        return clientDTO;
    }
}

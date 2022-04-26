package ru.kpfu.ds.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.ds.mainservice.model.dto.BaseClientDTO;
import ru.kpfu.ds.mainservice.model.dto.ClientStatisticDTO;
import ru.kpfu.ds.mainservice.model.dto.FullClientDTO;
import ru.kpfu.ds.mainservice.service.impl.ClientService;
import ru.kpfu.ds.mainservice.service.impl.SignUpService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/client")
public class ClientController {

    private final ClientService clientService;
    private final SignUpService signUpService;

    @GetMapping("/{clientId}")
    @PreAuthorize("@accessChecker.isClientMainInfoVisible(#clientId)")
    public BaseClientDTO getClient(@PathVariable Long clientId) {
        return clientService.getClient(clientId);
    }

    @GetMapping("/{clientId}/statistics")
    @PreAuthorize("@accessChecker.isClientStatisticVisible(#clientId)")
    public ClientStatisticDTO getCurrentClientStatistic(@PathVariable Long clientId) {
        return clientService.getClientStatistic(clientId);
    }

    @PostMapping
    public BaseClientDTO signUp(@RequestBody FullClientDTO formDTO) {
        return signUpService.signUp(formDTO);
    }
}

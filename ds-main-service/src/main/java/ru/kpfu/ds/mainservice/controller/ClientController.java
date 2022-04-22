package ru.kpfu.ds.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.ds.mainservice.model.dto.ClientDTO;
import ru.kpfu.ds.mainservice.model.dto.ClientStatisticDTO;
import ru.kpfu.ds.mainservice.service.impl.ClientService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/client/{clientId}")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @PreAuthorize("@accessChecker.isClientMainInfoVisible(#clientId)")
    public ClientDTO getClient(@PathVariable Long clientId) {
        return clientService.getClient(clientId);
    }

    @GetMapping("/statistics")
    @PreAuthorize("@accessChecker.isClientStatisticVisible(#clientId)")
    public ClientStatisticDTO getCurrentClientStatistic(@PathVariable Long clientId) {
        return clientService.getClientStatistic(clientId);
    }
}

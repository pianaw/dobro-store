package ru.kpfu.ds.mainservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.ds.mainservice.model.dto.ClientDTO;
import ru.kpfu.ds.mainservice.model.dto.ClientFormDTO;
import ru.kpfu.ds.mainservice.service.ClientSignUpService;

@RestController
@RequestMapping("/v1/ui/user/client")
@RequiredArgsConstructor
public class ClientSignUpController {

    private final ClientSignUpService clientSignUpService;

    @PostMapping
    public ClientDTO signUpClient(@RequestBody ClientFormDTO clientForm) {
        return clientSignUpService.signUp(clientForm);
    }
}
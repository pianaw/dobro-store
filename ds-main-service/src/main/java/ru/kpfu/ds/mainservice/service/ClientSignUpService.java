package ru.kpfu.ds.mainservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.ds.mainservice.model.dto.ClientDTO;
import ru.kpfu.ds.mainservice.model.dto.ClientFormDTO;
import ru.kpfu.ds.mainservice.model.entity.Client;
import ru.kpfu.ds.mainservice.model.entity.User;
import ru.kpfu.ds.mainservice.model.enums.ClientLevel;
import ru.kpfu.ds.mainservice.model.enums.UserRole;
import ru.kpfu.ds.mainservice.repository.ClientRepository;
import ru.kpfu.ds.mainservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ClientSignUpService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientDTO signUp(ClientFormDTO clientFormDTO) {

        Client client = Client.builder()
                .firstName(clientFormDTO.getFirstName())
                .lastName(clientFormDTO.getLastName())
                .patronymic(clientFormDTO.getPatronymic())
                .clientLevel(ClientLevel.LOW)
                .build();
        Client savedClient = clientRepository.save(client);
        User user = User.builder()
                .email(clientFormDTO.getEmail())
                .hashPassword(passwordEncoder.encode(clientFormDTO.getPassword()))
                .userRole(UserRole.CLIENT)
                .ownerId(savedClient.getId())
                .build();
        userRepository.save(user);
        return ClientDTO.builder()
                .firstName(savedClient.getFirstName())
                .lastName(savedClient.getLastName())
                .patronymic(savedClient.getPatronymic())
                .clientLevel(savedClient.getClientLevel())
                .build();
    }

}

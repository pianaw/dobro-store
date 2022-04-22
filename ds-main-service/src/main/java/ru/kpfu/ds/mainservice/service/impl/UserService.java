package ru.kpfu.ds.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.ds.mainservice.model.entity.User;
import ru.kpfu.ds.mainservice.model.enums.UserRole;
import ru.kpfu.ds.mainservice.model.exception.UserNotFoundException;
import ru.kpfu.ds.mainservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getByEmailOrThrow(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found with email [%s]", email)));
    }

    @Transactional(readOnly = true)
    public User getByRoleAndOwnerIdOrThrow(UserRole userRole, Long ownerId) {
        return userRepository.findByUserRoleAndOwnerId(userRole, ownerId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found with user role [%s] and ownerId [%s]", userRole, ownerId)));
    }
}

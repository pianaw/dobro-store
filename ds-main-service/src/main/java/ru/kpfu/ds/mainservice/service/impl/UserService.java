package ru.kpfu.ds.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.ds.mainservice.model.dto.UserDTO;
import ru.kpfu.ds.mainservice.model.entity.User;
import ru.kpfu.ds.mainservice.model.enums.UserRole;
import ru.kpfu.ds.mainservice.model.exception.UserAlreadyExistsException;
import ru.kpfu.ds.mainservice.model.exception.UserNotFoundException;
import ru.kpfu.ds.mainservice.model.mapper.UserMapper;
import ru.kpfu.ds.mainservice.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public User getByEmailOrThrow(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found with email [%s]", email)));
    }

    @Transactional(readOnly = true)
    public User getByRoleAndOwnerIdOrThrow(UserRole userRole, Long ownerId) {
        return userRepository.findByRoleAndOwnerId(userRole, ownerId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found with user role [%s] and ownerId [%s]", userRole, ownerId)));
    }

    @Transactional
    public void add(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        userRepository.save(user);
    }

    public void throwIfPresent(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("Such user already exists");
        }
    }
}

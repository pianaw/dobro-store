package ru.kpfu.ds.mainservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kpfu.ds.mainservice.model.entity.RedisUser;
import ru.kpfu.ds.mainservice.model.entity.User;
import ru.kpfu.ds.mainservice.repository.RedisUserRepository;
import ru.kpfu.ds.mainservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisService {

    private final RedisUserRepository redisUserRepository;
    private final UserRepository userRepository;

    public void saveUserJTI(UUID jti, User user) {
        UUID userRedisId = user.getRedisId();
        RedisUser redisUser;
        if (userRedisId != null) {
            redisUser = redisUserRepository.findById(userRedisId.toString())
                    .orElseThrow(IllegalArgumentException::new); //TODO:  возможно здесь можно заложить логику забаненого пользователя: если его нет в redis - он забанен
            if (redisUser.getTokens() == null) {
                redisUser.setTokens(new ArrayList<>());
            }
            redisUser.getTokens().add(jti.toString());
        } else {
            redisUser = RedisUser.builder()
                    .userId(user.getId())
                    .tokens(Collections.singletonList(jti.toString()))
                    .build();
        }

        redisUser = redisUserRepository.save(redisUser);

        if (userRedisId == null) {
            user.setRedisId(UUID.fromString(redisUser.getId()));
            userRepository.save(user);
        }
    }
}

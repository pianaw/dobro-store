package ru.kpfu.ds.mainservice.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.kpfu.ds.mainservice.model.entity.RedisUser;

public interface RedisUserRepository extends KeyValueRepository<RedisUser, String> {
}
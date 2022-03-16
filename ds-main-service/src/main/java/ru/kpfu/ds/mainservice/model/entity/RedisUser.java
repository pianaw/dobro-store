package ru.kpfu.ds.mainservice.model.entity;


import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.util.List;

@Data
@RedisHash("user")
public class RedisUser {

    @Id
    private String id;
    private List<String> tokens;
    private Long userId;
}
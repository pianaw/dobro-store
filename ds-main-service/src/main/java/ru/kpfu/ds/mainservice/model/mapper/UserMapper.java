package ru.kpfu.ds.mainservice.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kpfu.ds.mainservice.model.dto.UserDTO;
import ru.kpfu.ds.mainservice.model.entity.User;
import ru.kpfu.ds.mainservice.util.bcrypt.BCryptUtil;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE, uses = {BCryptUtil.class})
public interface UserMapper {

    @Mapping(target = "email", source = "credentials.email")
    @Mapping(target = "hashPassword", source = "credentials.password", qualifiedByName = "generateHashPassword")
    User toEntity(UserDTO source);
}

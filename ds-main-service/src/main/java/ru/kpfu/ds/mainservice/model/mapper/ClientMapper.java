package ru.kpfu.ds.mainservice.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.kpfu.ds.mainservice.model.dto.BaseClientDTO;
import ru.kpfu.ds.mainservice.model.dto.FullClientDTO;
import ru.kpfu.ds.mainservice.model.entity.Client;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ClientMapper {

    Client toEntity(BaseClientDTO source);

    BaseClientDTO toBaseClientDTO(Client target);

    @Mapping(target = "user.ownerId", source = "id")
    @Mapping(target = "user.role", constant = "CLIENT")
    FullClientDTO merge(@MappingTarget FullClientDTO target, Client source);
}

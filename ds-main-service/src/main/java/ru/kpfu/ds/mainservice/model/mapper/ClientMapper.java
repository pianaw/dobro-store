package ru.kpfu.ds.mainservice.model.mapper;

import org.mapstruct.Mapper;
import ru.kpfu.ds.mainservice.model.dto.ClientDTO;
import ru.kpfu.ds.mainservice.model.entity.Client;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ClientMapper {

    ClientDTO toDTO(Client source);
}

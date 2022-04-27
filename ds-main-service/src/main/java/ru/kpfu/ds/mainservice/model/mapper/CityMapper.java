package ru.kpfu.ds.mainservice.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.kpfu.ds.mainservice.model.dto.CityDTO;
import ru.kpfu.ds.mainservice.model.entity.City;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {

    CityDTO toDTO(City source);
}

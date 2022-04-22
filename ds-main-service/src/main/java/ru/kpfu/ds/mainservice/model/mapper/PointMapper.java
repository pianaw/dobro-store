package ru.kpfu.ds.mainservice.model.mapper;

import org.mapstruct.Mapper;
import ru.kpfu.ds.mainservice.model.dto.PointDTO;
import ru.kpfu.ds.mainservice.model.entity.Point;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface PointMapper {

    PointDTO toDTO(Point source);
}

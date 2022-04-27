package ru.kpfu.ds.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.ds.mainservice.model.dto.PointDTO;
import ru.kpfu.ds.mainservice.model.mapper.PointMapper;
import ru.kpfu.ds.mainservice.repository.PointRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;
    private final PointMapper pointMapper;

    @Transactional(readOnly = true)
    public List<PointDTO> getPointsByCityId(Long cityId) {
        return pointRepository.findAllByCity_Id(cityId).stream()
                .map(pointMapper::toDTO)
                .collect(Collectors.toList());
    }
}

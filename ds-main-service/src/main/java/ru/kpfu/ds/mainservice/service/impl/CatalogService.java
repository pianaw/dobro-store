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
public class CatalogService {

    private final PointRepository pointRepository;
    private final PointMapper pointMapper;

    @Transactional(readOnly = true)
    public List<PointDTO> getAllPointsByCity(String city) {
        return pointRepository.findAllByCity(city).stream()
                .map(pointMapper::toDTO)
                .collect(Collectors.toList());
    }
}

package ru.kpfu.ds.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.ds.mainservice.model.dto.CityDTO;
import ru.kpfu.ds.mainservice.model.dto.PointDTO;
import ru.kpfu.ds.mainservice.model.mapper.CityMapper;
import ru.kpfu.ds.mainservice.model.mapper.PointMapper;
import ru.kpfu.ds.mainservice.repository.CityRepository;
import ru.kpfu.ds.mainservice.repository.PointRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Transactional(readOnly = true)
    public List<CityDTO> getAll() {
        return cityRepository.findAll().stream()
                .map(cityMapper::toDTO)
                .collect(Collectors.toList());
    }
}

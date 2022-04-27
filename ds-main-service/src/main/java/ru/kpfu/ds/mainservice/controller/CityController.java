package ru.kpfu.ds.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.ds.mainservice.model.dto.CityDTO;
import ru.kpfu.ds.mainservice.service.impl.CityService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ui/cities")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public List<CityDTO> getAllCities() {
        return cityService.getAll();
    }
}

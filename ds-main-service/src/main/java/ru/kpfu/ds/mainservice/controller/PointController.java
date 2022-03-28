package ru.kpfu.ds.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.ds.mainservice.model.dto.PointDTO;
import ru.kpfu.ds.mainservice.service.CatalogService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ui/point")
@RequiredArgsConstructor
public class PointController {

    private final CatalogService catalogService;

    @GetMapping
    public List<PointDTO> getAllPointsByCity(@RequestParam String city) {
        return catalogService.getAllPointsByCity(city);
    }
}

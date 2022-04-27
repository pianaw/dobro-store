package ru.kpfu.ds.mainservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.ds.mainservice.model.dto.PointDTO;
import ru.kpfu.ds.mainservice.service.impl.PointService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ui/cities/{cityId}/points")
public class PointController {

    private final PointService pointService;

    @GetMapping
    @Operation(description = "Поиск пунктов приёма вторсырья по идентификатору города")
    public List<PointDTO> getPointsByCityId(@PathVariable Long cityId) {
        return pointService.getPointsByCityId(cityId);
    }
}

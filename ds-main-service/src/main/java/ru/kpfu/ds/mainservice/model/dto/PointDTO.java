package ru.kpfu.ds.mainservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class PointDTO {

    private Double latitude;
    private Double longitude;

}

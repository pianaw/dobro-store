package ru.kpfu.ds.mainservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointDTO {

    private Double latitude;
    private Double longitude;

}

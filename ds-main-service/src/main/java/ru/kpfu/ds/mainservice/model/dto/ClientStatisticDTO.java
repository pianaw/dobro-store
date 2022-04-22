package ru.kpfu.ds.mainservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.kpfu.ds.mainservice.model.enums.ObjectType;

import java.util.Map;

@Data
@AllArgsConstructor
public class ClientStatisticDTO {

    private Map<ObjectType, Integer> statistics;
}

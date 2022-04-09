package ru.kpfu.ds.mainservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {
    ACCESS_TOKEN("Access-token"),
    REFRESH_TOKEN("Refresh-token");

    private String name;
}
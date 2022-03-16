package ru.kpfu.ds.mainservice.model.enums.converter;

import ru.kpfu.ds.mainservice.model.enums.ClientLevel;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ClientLevelConverter implements AttributeConverter<ClientLevel, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ClientLevel clientLevel) {
        if (clientLevel == null) {
            return null;
        }
        return clientLevel.getValue();
    }

    @Override
    public ClientLevel convertToEntityAttribute(Integer clientLevelValue) {
        if (clientLevelValue == null) {
            return null;
        }

        return Stream.of(ClientLevel.values())
                .filter(p -> Objects.equals(p.getValue(), clientLevelValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

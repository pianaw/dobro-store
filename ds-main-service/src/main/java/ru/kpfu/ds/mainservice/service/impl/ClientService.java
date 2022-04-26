package ru.kpfu.ds.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.ds.mainservice.model.dto.BaseClientDTO;
import ru.kpfu.ds.mainservice.model.dto.ClientStatisticDTO;
import ru.kpfu.ds.mainservice.model.dto.FullClientDTO;
import ru.kpfu.ds.mainservice.model.entity.Client;
import ru.kpfu.ds.mainservice.model.entity.Object;
import ru.kpfu.ds.mainservice.model.enums.ObjectType;
import ru.kpfu.ds.mainservice.model.exception.ClientNotFoundException;
import ru.kpfu.ds.mainservice.model.mapper.ClientMapper;
import ru.kpfu.ds.mainservice.repository.ClientRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ObjectService objectService;
    private final ClientMapper clientMapper;

    public ClientStatisticDTO getClientStatistic(Long clientId) {
        List<Object> objects = objectService.getAllByClientId(clientId);

        Map<ObjectType, Integer> statistics = objects.stream()
                .collect(Collectors.groupingBy(Object::getObjectType))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, objectsGroupedByType -> objectsGroupedByType.getValue().size()));

        return new ClientStatisticDTO(statistics);
    }

    @Transactional(readOnly = true)
    public BaseClientDTO getClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(String.format("Client not found by id [%s]", clientId)));

        return clientMapper.toBaseClientDTO(client);
    }

    @Transactional
    public FullClientDTO add(FullClientDTO clientDTO) {
        Client savedClient = clientRepository.save(clientMapper.toEntity(clientDTO));
        return clientMapper.merge(clientDTO, savedClient);
    }
}

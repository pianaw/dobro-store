package ru.kpfu.ds.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.ds.mainservice.model.entity.Object;
import ru.kpfu.ds.mainservice.repository.ObjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectService {

    private final ObjectRepository objectRepository;

    @Transactional(readOnly = true)
    public List<Object> getAllByClientId(Long clientId) {
        return objectRepository.findAllByClient_Id(clientId);
    }
}

package ru.kpfu.ds.mainservice.security.access;

public interface AccessCheckerService {

    boolean isClientMainInfoVisible(Long clientId);

    boolean isClientStatisticVisible(Long clientId);

}

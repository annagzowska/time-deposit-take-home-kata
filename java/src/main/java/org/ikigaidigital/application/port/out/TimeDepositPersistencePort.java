package org.ikigaidigital.application.port.out;

import org.ikigaidigital.domain.TimeDeposit;

import java.util.List;

public interface TimeDepositPersistencePort {
    List<TimeDeposit> findAll();
    void saveAll(List<TimeDeposit> timeDeposits);
}

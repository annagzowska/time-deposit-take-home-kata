package org.ikigaidigital.application.service;

import lombok.RequiredArgsConstructor;
import org.ikigaidigital.application.port.in.GetTimeDepositsUseCase;
import org.ikigaidigital.application.port.in.UpdateTimeDepositsBalanceUseCase;
import org.ikigaidigital.application.port.out.TimeDepositPersistencePort;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.ikigaidigital.domain.service.TimeDepositCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeDepositService implements GetTimeDepositsUseCase, UpdateTimeDepositsBalanceUseCase {

    private final TimeDepositPersistencePort timeDepositPersistencePort;
    private final TimeDepositCalculator timeDepositCalculator;

    @Override
    public List<TimeDeposit> getTimeDeposits() {
        return timeDepositPersistencePort.findAll();
    }

    @Override
    public void updateBalancesForAllTimeDeposits() {
        List<TimeDeposit> allDeposits = timeDepositPersistencePort.findAll();
        timeDepositCalculator.updateBalance(allDeposits);
        timeDepositPersistencePort.saveAll(allDeposits);
    }

}

package org.ikigaidigital.application.port.in;

import org.ikigaidigital.domain.TimeDeposit;

import java.util.List;

public interface UpdateTimeDepositsBalanceUseCase {

    void updateBalance(List<TimeDeposit> timeDeposits);
}

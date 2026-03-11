package org.ikigaidigital.application.service;

import org.ikigaidigital.adapter.in.web.dto.TimeDepositDto;
import org.ikigaidigital.application.port.in.GetTimeDepositsUseCase;
import org.ikigaidigital.application.port.in.UpdateTimeDepositsBalanceUseCase;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TimeDepositService implements GetTimeDepositsUseCase, UpdateTimeDepositsBalanceUseCase {


    @Override
    public List<TimeDepositDto> getTimeDeposits() {
        return Collections.emptyList();
    }

}

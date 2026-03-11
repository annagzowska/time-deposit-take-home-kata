package org.ikigaidigital.application.port.in;

import org.ikigaidigital.adapter.in.web.dto.TimeDepositDto;

import java.util.List;

public interface GetTimeDepositsUseCase {

    List<TimeDepositDto> getTimeDeposits();
}

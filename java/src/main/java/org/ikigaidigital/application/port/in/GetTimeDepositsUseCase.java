package org.ikigaidigital.application.port.in;

import org.ikigaidigital.domain.model.TimeDeposit;

import java.util.List;

public interface GetTimeDepositsUseCase {

    List<TimeDeposit> getTimeDeposits();
}

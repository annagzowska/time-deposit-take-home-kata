package org.ikigaidigital.application.service;

import lombok.RequiredArgsConstructor;
import org.ikigaidigital.adapter.in.web.dto.TimeDepositDto;
import org.ikigaidigital.adapter.out.TimeDepositRepository;
import org.ikigaidigital.application.port.in.GetTimeDepositsUseCase;
import org.ikigaidigital.application.port.in.UpdateTimeDepositsBalanceUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeDepositService implements GetTimeDepositsUseCase, UpdateTimeDepositsBalanceUseCase {

    private final TimeDepositRepository timeDepositRepository;

    @Override
    public List<TimeDepositDto> getTimeDeposits() {
        return timeDepositRepository.findAll().stream().map(TimeDepositDto::fromEntity).collect(Collectors.toList());
    }
}

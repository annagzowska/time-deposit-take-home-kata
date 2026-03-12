package org.ikigaidigital.adapter.in.web.mapper;

import lombok.NoArgsConstructor;
import org.ikigaidigital.adapter.in.web.dto.TimeDepositDto;
import org.ikigaidigital.adapter.in.web.dto.WithdrawalDto;
import org.ikigaidigital.domain.model.TimeDeposit;

import java.util.List;

@NoArgsConstructor
public class TimeDepositWebMapper {

    public static TimeDepositDto toDto(TimeDeposit timeDeposit) {
        final List<WithdrawalDto> withdrawals = timeDeposit.getWithdrawals().stream().map(WithdrawalWebMapper::toDto).toList();
        return TimeDepositDto.builder()
                .id(timeDeposit.getId())
                .planType(timeDeposit.getPlanType())
                .balance(timeDeposit.getBalance())
                .days(timeDeposit.getDays())
                .withdrawals(withdrawals)
                .build();
    }
}

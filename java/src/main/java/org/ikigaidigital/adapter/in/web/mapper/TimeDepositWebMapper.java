package org.ikigaidigital.adapter.in.web.mapper;

import lombok.NoArgsConstructor;
import org.ikigaidigital.adapter.in.web.dto.TimeDepositDto;
import org.ikigaidigital.domain.TimeDeposit;

import java.util.Collections;

@NoArgsConstructor
public class TimeDepositWebMapper {

    public static TimeDepositDto toDto(TimeDeposit timeDeposit) {
        return new TimeDepositDto(timeDeposit.getId(), timeDeposit.getPlanType(), timeDeposit.getBalance(), timeDeposit.getDays());
    }

    public static TimeDeposit toDomain(TimeDepositDto timeDepositDto) {
        return new TimeDeposit(timeDepositDto.id(), timeDepositDto.planType(), timeDepositDto.balance(), timeDepositDto.days(), Collections.emptyList());
    }
}

package org.ikigaidigital.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.ikigaidigital.adapter.in.web.dto.TimeDepositDto;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class TimeDeposit {
    private int id;
    private PlanType planType;
    @Setter
    private BigDecimal balance;
    private int days;

    public static TimeDeposit fromDto(TimeDepositDto dto) {
        return new TimeDeposit(dto.id(), dto.planType(), dto.balance(), dto.days());
    }
}

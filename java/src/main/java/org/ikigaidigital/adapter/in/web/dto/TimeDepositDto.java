package org.ikigaidigital.adapter.in.web.dto;

import org.ikigaidigital.domain.PlanType;
import org.ikigaidigital.domain.TimeDeposit;

import java.math.BigDecimal;

public record TimeDepositDto(int id, PlanType planType, BigDecimal balance, int days) {

    public static TimeDepositDto fromEntity(TimeDeposit timeDeposit) {
        return new TimeDepositDto(timeDeposit.getId(), timeDeposit.getPlanType(), timeDeposit.getBalance(), timeDeposit.getDays());
    }
}
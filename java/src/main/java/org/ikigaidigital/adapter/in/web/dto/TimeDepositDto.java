package org.ikigaidigital.adapter.in.web.dto;

import org.ikigaidigital.domain.TimeDeposit;

public record TimeDepositDto(int id, String planType, Double balance, int days) {

    public static TimeDepositDto fromEntity(TimeDeposit timeDeposit) {
        return new TimeDepositDto(timeDeposit.getId(), timeDeposit.getPlanType().name(), timeDeposit.getBalance().doubleValue(), timeDeposit.getDays());
    }
}
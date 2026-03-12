package org.ikigaidigital.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class TimeDeposit {
    private int id;
    private PlanType planType;
    @Setter
    private BigDecimal balance;
    private int days;
    private List<Withdrawal> withdrawals;
}

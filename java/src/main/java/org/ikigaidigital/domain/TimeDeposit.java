package org.ikigaidigital.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class TimeDeposit {
    private int id;
    private PlanType planType;
    @Setter
    private BigDecimal balance;
    private int days;
    private List<Withdrawal> withdrawals;
}

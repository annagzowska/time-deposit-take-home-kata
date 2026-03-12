package org.ikigaidigital.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Withdrawal {

    private int id;
    private int timeDepositId;
    private BigDecimal amount;
    private LocalDate date;
}

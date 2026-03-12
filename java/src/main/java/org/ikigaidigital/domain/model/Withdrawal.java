package org.ikigaidigital.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class Withdrawal {

    private int id;
    private BigDecimal amount;
    private LocalDate date;
}

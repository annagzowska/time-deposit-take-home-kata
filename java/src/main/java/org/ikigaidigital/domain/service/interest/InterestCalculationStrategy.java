package org.ikigaidigital.domain.service.interest;

import org.ikigaidigital.domain.PlanType;
import org.ikigaidigital.domain.TimeDeposit;

import java.math.BigDecimal;

public interface InterestCalculationStrategy {
    boolean supports(PlanType planType);
    BigDecimal calculate(TimeDeposit timeDeposit);
}

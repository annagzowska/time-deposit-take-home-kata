package org.ikigaidigital.domain.service.interest;

import org.ikigaidigital.domain.model.PlanType;
import org.ikigaidigital.domain.model.TimeDeposit;

import java.math.BigDecimal;

public interface InterestCalculationStrategy {
    boolean supports(PlanType planType);
    BigDecimal calculate(TimeDeposit timeDeposit);
}

package org.ikigaidigital.domain.service.interest;

import org.ikigaidigital.domain.model.PlanType;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class BasicInterestCalculationStrategy implements InterestCalculationStrategy {

    private static final int ZERO_INTEREST_DAYS = 30;
    private static final int MONTHS_IN_YEAR = 12;
    private static final BigDecimal INTEREST_RATE = BigDecimal.valueOf(0.01);

    @Override
    public boolean supports(PlanType planType) {
        return PlanType.BASIC.equals(planType);
    }

    @Override
    public BigDecimal calculate(TimeDeposit timeDeposit) {
        if (timeDeposit.getDays() <= ZERO_INTEREST_DAYS) {
            return BigDecimal.ZERO;
        }

        return timeDeposit.getBalance()
                .multiply(INTEREST_RATE)
                .divide(BigDecimal.valueOf(MONTHS_IN_YEAR), 2, RoundingMode.HALF_UP);
    }
}

package org.ikigaidigital.domain.service.interest;

import lombok.RequiredArgsConstructor;
import org.ikigaidigital.domain.model.PlanType;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
@RequiredArgsConstructor
public class StudentInterestCalculationStrategy implements InterestCalculationStrategy{
    private static final int ZERO_INTEREST_DAYS = 30;
    private static final int ZERO_INTEREST_START_DAY = 366;
    private static final int MONTHS = 12;
    private static final BigDecimal INTEREST_RATE = BigDecimal.valueOf(0.03);

    @Override
    public boolean supports(PlanType planType) {
        return PlanType.STUDENT.equals(planType);
    }

    @Override
    public BigDecimal calculate(TimeDeposit timeDeposit) {
        if (timeDeposit.getDays() <= ZERO_INTEREST_DAYS) {
            return BigDecimal.ZERO;
        } else if(timeDeposit.getDays() < ZERO_INTEREST_START_DAY) {
            return timeDeposit.getBalance()
                    .multiply(INTEREST_RATE)
                    .divide(BigDecimal.valueOf(MONTHS), 2, RoundingMode.HALF_UP);
        } else {
            return BigDecimal.ZERO;
        }
    }
}

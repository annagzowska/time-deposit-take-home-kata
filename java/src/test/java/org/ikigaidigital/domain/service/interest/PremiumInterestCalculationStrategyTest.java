package org.ikigaidigital.domain.service.interest;

import org.ikigaidigital.domain.model.PlanType;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PremiumInterestCalculationStrategyTest {

    private final PremiumInterestCalculationStrategy strategy = new PremiumInterestCalculationStrategy();

    @ParameterizedTest
    @CsvSource({
            "BASIC, false",
            "STUDENT, false",
            "PREMIUM, true"
    })
    void shouldSupportOnlyPremiumPlanType(PlanType planType, boolean expected) {
        //when
        boolean result = strategy.supports(planType);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "10, 0.00",
            "30, 0.00",
            "45, 0.00",
            "46, 5.00",
            "90, 5.00"
    })
    void shouldCalculateInterestForPremiumPlan(int days, String expectedInterest) {
        //given
        TimeDeposit timeDeposit = new TimeDeposit(
                1,
                PlanType.PREMIUM,
                BigDecimal.valueOf(1200.00),
                days,
                List.of()
        );

        //when
        BigDecimal result = strategy.calculate(timeDeposit);

        //then
        assertThat(result).isEqualByComparingTo(expectedInterest);
    }
}
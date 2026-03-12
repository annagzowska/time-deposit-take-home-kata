package org.ikigaidigital.domain.service.interest;

import org.ikigaidigital.domain.model.PlanType;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BasicInterestCalculationStrategyTest {

    private final BasicInterestCalculationStrategy strategy = new BasicInterestCalculationStrategy();

    @ParameterizedTest
    @CsvSource({
            "BASIC, true",
            "STUDENT, false",
            "PREMIUM, false"
    })
    void shouldSupportOnlyBasicPlanType(PlanType planType, boolean expected) {
        //when
        boolean result = strategy.supports(planType);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "10, 0.00",
            "30, 0.00",
            "31, 1.00",
            "45, 1.00"
    })
    void shouldCalculateInterestForBasicPlan(int days, String expectedInterest) {
        //given
        TimeDeposit timeDeposit = new TimeDeposit(
                1,
                PlanType.BASIC,
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
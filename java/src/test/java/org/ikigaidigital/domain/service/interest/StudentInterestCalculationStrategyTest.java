package org.ikigaidigital.domain.service.interest;

import org.ikigaidigital.domain.model.PlanType;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudentInterestCalculationStrategyTest {

    private final StudentInterestCalculationStrategy strategy = new StudentInterestCalculationStrategy();

    @ParameterizedTest
    @CsvSource({
            "BASIC, false",
            "STUDENT, true",
            "PREMIUM, false"
    })
    void shouldSupportOnlyStudentPlanType(PlanType planType, boolean expected) {
        //when
        boolean result = strategy.supports(planType);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "10, 0.00",
            "30, 0.00",
            "31, 3.00",
            "120, 3.00",
            "365, 3.00",
            "366, 0.00",
            "400, 0.00"
    })
    void shouldCalculateInterestForStudentPlan(int days, String expectedInterest) {
        //given
        TimeDeposit timeDeposit = new TimeDeposit(
                1,
                PlanType.STUDENT,
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
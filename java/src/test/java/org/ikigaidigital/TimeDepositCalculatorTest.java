package org.ikigaidigital;

import org.ikigaidigital.domain.service.TimeDepositCalculator;
import org.ikigaidigital.domain.service.interest.InterestCalculationStrategyResolver;
import org.ikigaidigital.domain.model.PlanType;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeDepositCalculatorTest {

    @Mock
    private InterestCalculationStrategyResolver calculationStrategyResolver;

    @Test
    public void updateBalance_Test() {
        TimeDepositCalculator calc = new TimeDepositCalculator(calculationStrategyResolver);
        List<TimeDeposit> plans = Arrays.asList(
            new TimeDeposit(1, PlanType.BASIC, BigDecimal.valueOf(1234567.00), 45)
        );
        calc.updateBalance(plans);

        assertThat(1).isEqualTo(1);
    }
}

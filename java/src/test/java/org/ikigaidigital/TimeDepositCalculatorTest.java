package org.ikigaidigital;

import org.ikigaidigital.domain.service.TimeDepositCalculator;
import org.ikigaidigital.domain.service.interest.InterestCalculationStrategy;
import org.ikigaidigital.domain.service.interest.InterestCalculationStrategyResolver;
import org.ikigaidigital.domain.model.PlanType;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TimeDepositCalculatorTest {

    @Mock
    private InterestCalculationStrategyResolver calculationStrategyResolver;

    @Mock
    private InterestCalculationStrategy interestCalculationStrategy;

    @InjectMocks
    private TimeDepositCalculator timeDepositCalculator;

    @Test
    void shouldThrowExceptionWhenTimeDepositsIsNull() {
        TimeDepositCalculator calculator = new TimeDepositCalculator(calculationStrategyResolver);

        assertThatThrownBy(() -> calculator.updateBalance(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Time deposits cannot be null");
    }

    @Test
    void shouldUpdateBalanceUsingResolvedStrategy() {
        //given
        TimeDeposit deposit = new TimeDeposit(
                1,
                PlanType.BASIC,
                BigDecimal.valueOf(1000.00),
                45,
                List.of()
        );

        when(calculationStrategyResolver.resolve(PlanType.BASIC)).thenReturn(interestCalculationStrategy);
        when(interestCalculationStrategy.calculate(deposit)).thenReturn(BigDecimal.valueOf(1.25));

        //when
        timeDepositCalculator.updateBalance(List.of(deposit));

        //then
        assertThat(deposit.getBalance()).isEqualByComparingTo("1001.25");
        verify(calculationStrategyResolver).resolve(PlanType.BASIC);
        verify(interestCalculationStrategy).calculate(deposit);
    }

    @Test
    void shouldUpdateBalancesForMultipleTimeDeposits() {
        //given
        TimeDeposit basicDeposit = new TimeDeposit(
                1,
                PlanType.BASIC,
                BigDecimal.valueOf(1000.00),
                45,
                List.of()
        );

        TimeDeposit premiumDeposit = new TimeDeposit(
                2,
                PlanType.PREMIUM,
                BigDecimal.valueOf(3000.00),
                90,
                List.of()
        );

        when(calculationStrategyResolver.resolve(PlanType.BASIC)).thenReturn(interestCalculationStrategy);
        when(calculationStrategyResolver.resolve(PlanType.PREMIUM)).thenReturn(interestCalculationStrategy);
        when(interestCalculationStrategy.calculate(basicDeposit)).thenReturn(BigDecimal.valueOf(1.25));
        when(interestCalculationStrategy.calculate(premiumDeposit)).thenReturn(BigDecimal.valueOf(12.50));

        //when
        timeDepositCalculator.updateBalance(List.of(basicDeposit, premiumDeposit));

        //then
        assertThat(basicDeposit.getBalance()).isEqualByComparingTo("1001.25");
        assertThat(premiumDeposit.getBalance()).isEqualByComparingTo("3012.50");

        verify(calculationStrategyResolver).resolve(PlanType.BASIC);
        verify(calculationStrategyResolver).resolve(PlanType.PREMIUM);
        verify(interestCalculationStrategy).calculate(basicDeposit);
        verify(interestCalculationStrategy).calculate(premiumDeposit);
    }

    @Test
    void shouldRoundCalculatedInterestBeforeAddingToBalance() {
        //given
        TimeDeposit deposit = new TimeDeposit(
                1,
                PlanType.STUDENT,
                BigDecimal.valueOf(1000.00),
                120,
                List.of()
        );

        when(calculationStrategyResolver.resolve(PlanType.STUDENT)).thenReturn(interestCalculationStrategy);
        when(interestCalculationStrategy.calculate(deposit)).thenReturn(new BigDecimal("1.235"));

        //when
        timeDepositCalculator.updateBalance(List.of(deposit));

        //then
        assertThat(deposit.getBalance()).isEqualByComparingTo("1001.24");
        verify(calculationStrategyResolver).resolve(PlanType.STUDENT);
        verify(interestCalculationStrategy).calculate(deposit);
    }
}

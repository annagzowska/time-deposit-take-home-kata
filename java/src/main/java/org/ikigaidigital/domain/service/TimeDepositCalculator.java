package org.ikigaidigital.domain.service;

import lombok.RequiredArgsConstructor;
import org.ikigaidigital.domain.service.interest.InterestCalculationStrategy;
import org.ikigaidigital.domain.service.interest.InterestCalculationStrategyResolver;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeDepositCalculator {

    private final InterestCalculationStrategyResolver calculationStrategyResolver;

    public void updateBalance(List<TimeDeposit> timeDeposits) {
        if (timeDeposits == null) {
            throw new IllegalArgumentException("Time deposits cannot be null");
        }
        timeDeposits.forEach(deposit -> {
            final InterestCalculationStrategy strategy = calculationStrategyResolver.resolve(deposit.getPlanType());
            final BigDecimal roundedInterest = strategy.calculate(deposit).setScale(2, RoundingMode.HALF_UP);
            final BigDecimal updatedBalance = deposit.getBalance().add(roundedInterest);
            deposit.setBalance(updatedBalance);
        });
    }
}

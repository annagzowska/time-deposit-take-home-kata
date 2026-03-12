package org.ikigaidigital.application.service;

import lombok.RequiredArgsConstructor;
import org.ikigaidigital.adapter.out.TimeDepositRepository;
import org.ikigaidigital.application.port.in.UpdateTimeDepositsBalanceUseCase;
import org.ikigaidigital.application.service.interest.InterestCalculationStrategy;
import org.ikigaidigital.application.service.interest.InterestCalculationStrategyResolver;
import org.ikigaidigital.domain.TimeDeposit;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeDepositCalculator implements UpdateTimeDepositsBalanceUseCase {

    private final InterestCalculationStrategyResolver calculationStrategyResolver;
    private final TimeDepositRepository timeDepositRepository;

    @Override
    public void updateBalance(List<TimeDeposit> xs) {
        xs.forEach(x -> {
            final InterestCalculationStrategy strategy = calculationStrategyResolver.resolve(x.getPlanType());
            final BigDecimal roundedInterest = strategy.calculate(x).setScale(2, RoundingMode.HALF_UP);
            final BigDecimal updatedBalance = x.getBalance().add(roundedInterest);
            x.setBalance(updatedBalance);
            timeDepositRepository.save(x);
        });
    }
}

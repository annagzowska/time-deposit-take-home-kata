package org.ikigaidigital.domain.service.interest;

import lombok.RequiredArgsConstructor;
import org.ikigaidigital.domain.PlanType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestCalculationStrategyResolver {

    private final List<InterestCalculationStrategy> strategies;

    public InterestCalculationStrategy resolve(PlanType planType) {
        return strategies.stream()
                .filter(strategy -> strategy.supports(planType))
                .findFirst()
                .orElseThrow();
    }
}

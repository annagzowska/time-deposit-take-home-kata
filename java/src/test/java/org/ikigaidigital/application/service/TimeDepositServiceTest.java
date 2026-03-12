package org.ikigaidigital.application.service;

import org.ikigaidigital.application.port.out.TimeDepositPersistencePort;
import org.ikigaidigital.domain.model.PlanType;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.ikigaidigital.domain.service.TimeDepositCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimeDepositServiceTest {

    @Mock
    private TimeDepositPersistencePort timeDepositPersistencePort;

    @Mock
    private TimeDepositCalculator timeDepositCalculator;

    @InjectMocks
    private TimeDepositService timeDepositService;

    @Test
    void shouldReturnAllTimeDeposits() {
        List<TimeDeposit> timeDeposits = List.of(
                new TimeDeposit(1, PlanType.BASIC, BigDecimal.valueOf(1000.00), 60, List.of()),
                new TimeDeposit(2, PlanType.STUDENT, BigDecimal.valueOf(1500.00), 120, List.of())
        );

        when(timeDepositPersistencePort.findAll()).thenReturn(timeDeposits);

        List<TimeDeposit> result = timeDepositService.getTimeDeposits();

        assertThat(result).isEqualTo(timeDeposits);
        verify(timeDepositPersistencePort).findAll();
    }

    @Test
    void shouldUpdateBalancesForAllTimeDeposits() {
        List<TimeDeposit> timeDeposits = List.of(
                new TimeDeposit(1, PlanType.BASIC, BigDecimal.valueOf(1000.00), 60, List.of()),
                new TimeDeposit(2, PlanType.PREMIUM, BigDecimal.valueOf(3000.00), 90, List.of())
        );

        when(timeDepositPersistencePort.findAll()).thenReturn(timeDeposits);

        timeDepositService.updateBalancesForAllTimeDeposits();

        InOrder inOrder = inOrder(timeDepositPersistencePort, timeDepositCalculator);
        inOrder.verify(timeDepositPersistencePort).findAll();
        inOrder.verify(timeDepositCalculator).updateBalance(timeDeposits);
        inOrder.verify(timeDepositPersistencePort).saveAll(timeDeposits);
    }
}
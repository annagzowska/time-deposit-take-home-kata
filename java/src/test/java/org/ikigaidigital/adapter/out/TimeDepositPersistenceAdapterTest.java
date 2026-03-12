package org.ikigaidigital.adapter.out;

import org.ikigaidigital.adapter.out.persistence.entity.TimeDepositEntity;
import org.ikigaidigital.adapter.out.persistence.entity.WithdrawalEntity;
import org.ikigaidigital.adapter.out.persistence.repository.SpringDataTimeDepositRepository;
import org.ikigaidigital.domain.model.PlanType;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.ikigaidigital.domain.model.Withdrawal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TimeDepositPersistenceAdapterTest {

    @Mock
    private SpringDataTimeDepositRepository timeDepositRepository;

    @InjectMocks
    private TimeDepositPersistenceAdapter adapter;

    @Test
    void shouldFindAllTimeDeposits() {
        //given
        TimeDepositEntity timeDepositEntity = TimeDepositEntity.builder()
                .id(1)
                .planType("basic")
                .balance(BigDecimal.valueOf(1000.00))
                .days(60)
                .build();

        WithdrawalEntity withdrawalEntity = WithdrawalEntity.builder()
                .id(10)
                .timeDeposit(timeDepositEntity)
                .amount(BigDecimal.valueOf(100.00))
                .date(LocalDate.of(2026, 3, 1))
                .build();

        timeDepositEntity.setWithdrawals(List.of(withdrawalEntity));

        when(timeDepositRepository.findAll()).thenReturn(List.of(timeDepositEntity));

        //when
        List<TimeDeposit> result = adapter.findAll();

        //then
        assertThat(result).hasSize(1);

        TimeDeposit timeDeposit = result.get(0);
        assertThat(timeDeposit.getId()).isEqualTo(1);
        assertThat(timeDeposit.getPlanType()).isEqualTo(PlanType.BASIC);
        assertThat(timeDeposit.getBalance()).isEqualByComparingTo("1000.00");
        assertThat(timeDeposit.getDays()).isEqualTo(60);
        assertThat(timeDeposit.getWithdrawals()).hasSize(1);

        Withdrawal withdrawal = timeDeposit.getWithdrawals().get(0);
        assertThat(withdrawal.getId()).isEqualTo(10);
        assertThat(withdrawal.getAmount()).isEqualByComparingTo("100.00");
        assertThat(withdrawal.getDate()).isEqualTo(LocalDate.of(2026, 3, 1));

        verify(timeDepositRepository).findAll();
    }

    @Test
    void shouldSaveAllMappedEntities() {
        //given
        Withdrawal withdrawal = Withdrawal.builder()
                .id(20)
                .amount(BigDecimal.valueOf(50.00))
                .date(LocalDate.of(2026, 3, 5))
                .build();

        TimeDeposit timeDeposit = new TimeDeposit(
                2,
                PlanType.STUDENT,
                BigDecimal.valueOf(1500.00),
                120,
                List.of(withdrawal)
        );

        //when
        adapter.saveAll(List.of(timeDeposit));

        //then
        ArgumentCaptor<List<TimeDepositEntity>> captor = ArgumentCaptor.forClass(List.class);
        verify(timeDepositRepository).saveAll(captor.capture());

        List<TimeDepositEntity> savedEntities = captor.getValue();
        assertThat(savedEntities).hasSize(1);

        TimeDepositEntity savedEntity = savedEntities.get(0);
        assertThat(savedEntity.getId()).isEqualTo(2);
        assertThat(savedEntity.getPlanType()).isEqualTo("student");
        assertThat(savedEntity.getBalance()).isEqualByComparingTo("1500.00");
        assertThat(savedEntity.getDays()).isEqualTo(120);
        assertThat(savedEntity.getWithdrawals()).hasSize(1);

        WithdrawalEntity savedWithdrawal = savedEntity.getWithdrawals().get(0);
        assertThat(savedWithdrawal.getId()).isEqualTo(20);
        assertThat(savedWithdrawal.getAmount()).isEqualByComparingTo("50.00");
        assertThat(savedWithdrawal.getDate()).isEqualTo(LocalDate.of(2026, 3, 5));
        assertThat(savedWithdrawal.getTimeDeposit()).isSameAs(savedEntity);
    }
}
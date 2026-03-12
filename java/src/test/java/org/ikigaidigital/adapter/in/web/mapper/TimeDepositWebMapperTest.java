package org.ikigaidigital.adapter.in.web.mapper;

import org.ikigaidigital.adapter.in.web.dto.TimeDepositDto;
import org.ikigaidigital.adapter.in.web.dto.WithdrawalDto;
import org.ikigaidigital.domain.model.PlanType;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.ikigaidigital.domain.model.Withdrawal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TimeDepositWebMapperTest {

    @Test
    void shouldMapTimeDepositToDtoWithAllFields() {
        //given
        Withdrawal firstWithdrawal = Withdrawal.builder()
                .id(1)
                .amount(BigDecimal.valueOf(100.00))
                .date(LocalDate.of(2026, 3, 1))
                .build();

        Withdrawal secondWithdrawal = Withdrawal.builder()
                .id(2)
                .amount(BigDecimal.valueOf(50.00))
                .date(LocalDate.of(2026, 3, 5))
                .build();

        TimeDeposit timeDeposit = new TimeDeposit(
                10,
                PlanType.BASIC,
                BigDecimal.valueOf(1000.00),
                60,
                List.of(firstWithdrawal, secondWithdrawal)
        );

        //when
        TimeDepositDto result = TimeDepositWebMapper.toDto(timeDeposit);

        //then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(10);
        assertThat(result.planType()).isEqualTo(PlanType.BASIC);
        assertThat(result.balance()).isEqualByComparingTo("1000.00");
        assertThat(result.days()).isEqualTo(60);
        assertThat(result.withdrawals()).hasSize(2);

        WithdrawalDto firstResultWithdrawal = result.withdrawals().get(0);
        assertThat(firstResultWithdrawal.id()).isEqualTo(1);
        assertThat(firstResultWithdrawal.amount()).isEqualByComparingTo("100.0");
        assertThat(firstResultWithdrawal.date()).isEqualTo(LocalDate.of(2026, 3, 1));

        WithdrawalDto secondResultWithdrawal = result.withdrawals().get(1);
        assertThat(secondResultWithdrawal.id()).isEqualTo(2);
        assertThat(secondResultWithdrawal.amount()).isEqualByComparingTo("50.0");
        assertThat(secondResultWithdrawal.date()).isEqualTo(LocalDate.of(2026, 3, 5));
    }

    @Test
    void shouldMapTimeDepositToDtoWithEmptyWithdrawalsList() {
        //given
        TimeDeposit timeDeposit = new TimeDeposit(
                11,
                PlanType.PREMIUM,
                BigDecimal.valueOf(3000.00),
                90,
                List.of()
        );

        //when
        TimeDepositDto result = TimeDepositWebMapper.toDto(timeDeposit);

        //then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(11);
        assertThat(result.planType()).isEqualTo(PlanType.PREMIUM);
        assertThat(result.balance()).isEqualByComparingTo("3000.00");
        assertThat(result.days()).isEqualTo(90);
        assertThat(result.withdrawals()).isEmpty();
    }
}
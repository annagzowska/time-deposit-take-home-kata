package org.ikigaidigital.adapter.out.persistence.mapper;

import org.ikigaidigital.adapter.out.persistence.entity.TimeDepositEntity;
import org.ikigaidigital.adapter.out.persistence.entity.WithdrawalEntity;
import org.ikigaidigital.domain.model.PlanType;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.ikigaidigital.domain.model.Withdrawal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TimeDepositPersistenceMapperTest {
    @Test
    void shouldMapTimeDepositToEntity() {
        //given
        Withdrawal withdrawal = Withdrawal.builder()
                .id(1)
                .amount(BigDecimal.valueOf(100.00))
                .date(LocalDate.of(2026, 3, 1))
                .build();

        TimeDeposit timeDeposit = new TimeDeposit(
                10,
                PlanType.BASIC,
                BigDecimal.valueOf(1000.00),
                60,
                List.of(withdrawal)
        );

        //when
        TimeDepositEntity result = TimeDepositPersistenceMapper.toEntity(timeDeposit);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(10);
        assertThat(result.getPlanType()).isEqualTo("basic");
        assertThat(result.getBalance()).isEqualByComparingTo("1000.00");
        assertThat(result.getDays()).isEqualTo(60);
        assertThat(result.getWithdrawals()).hasSize(1);

        WithdrawalEntity mappedWithdrawal = result.getWithdrawals().get(0);
        assertThat(mappedWithdrawal.getId()).isEqualTo(1);
        assertThat(mappedWithdrawal.getAmount()).isEqualByComparingTo("100.00");
        assertThat(mappedWithdrawal.getDate()).isEqualTo(LocalDate.of(2026, 3, 1));
        assertThat(mappedWithdrawal.getTimeDeposit()).isSameAs(result);
    }

    @Test
    void shouldMapTimeDepositToEntityWithEmptyWithdrawalsWhenSourceWithdrawalsIsNull() {
        //given
        TimeDeposit timeDeposit = new TimeDeposit(
                11,
                PlanType.PREMIUM,
                BigDecimal.valueOf(3000.00),
                90,
                null
        );

        //when
        TimeDepositEntity result = TimeDepositPersistenceMapper.toEntity(timeDeposit);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(11);
        assertThat(result.getPlanType()).isEqualTo("premium");
        assertThat(result.getBalance()).isEqualByComparingTo("3000.00");
        assertThat(result.getDays()).isEqualTo(90);
        assertThat(result.getWithdrawals()).isEmpty();
    }

    @Test
    void shouldThrowExceptionWhenTimeDepositIsNull() {
        assertThatThrownBy(() -> TimeDepositPersistenceMapper.toEntity(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Time deposit cannot be null");
    }

    @Test
    void shouldMapEntityToDomain() {
        //given
        TimeDepositEntity timeDepositEntity = TimeDepositEntity.builder()
                .id(20)
                .planType("student")
                .balance(BigDecimal.valueOf(1500.00))
                .days(120)
                .build();

        WithdrawalEntity withdrawalEntity = WithdrawalEntity.builder()
                .id(2)
                .timeDeposit(timeDepositEntity)
                .amount(BigDecimal.valueOf(50.00))
                .date(LocalDate.of(2026, 3, 5))
                .build();

        timeDepositEntity.setWithdrawals(List.of(withdrawalEntity));

        //when
        TimeDeposit result = TimeDepositPersistenceMapper.toDomain(timeDepositEntity);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(20);
        assertThat(result.getPlanType()).isEqualTo(PlanType.STUDENT);
        assertThat(result.getBalance()).isEqualByComparingTo("1500.00");
        assertThat(result.getDays()).isEqualTo(120);
        assertThat(result.getWithdrawals()).hasSize(1);

        Withdrawal mappedWithdrawal = result.getWithdrawals().get(0);
        assertThat(mappedWithdrawal.getId()).isEqualTo(2);
        assertThat(mappedWithdrawal.getAmount()).isEqualByComparingTo("50.00");
        assertThat(mappedWithdrawal.getDate()).isEqualTo(LocalDate.of(2026, 3, 5));
    }

}
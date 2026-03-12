package org.ikigaidigital.adapter.out.persistence.mapper;

import org.ikigaidigital.adapter.out.persistence.entity.TimeDepositEntity;
import org.ikigaidigital.adapter.out.persistence.entity.WithdrawalEntity;
import org.ikigaidigital.domain.model.Withdrawal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class WithdrawalPersistenceMapperTest {

    @Test
    void shouldMapWithdrawalEntityToDomain() {
        TimeDepositEntity timeDepositEntity = TimeDepositEntity.builder()
                .id(10)
                .planType("basic")
                .balance(BigDecimal.valueOf(1000.00))
                .days(60)
                .build();

        WithdrawalEntity withdrawalEntity = WithdrawalEntity.builder()
                .id(1)
                .timeDeposit(timeDepositEntity)
                .amount(BigDecimal.valueOf(100.00))
                .date(LocalDate.of(2026, 3, 1))
                .build();

        Withdrawal result = WithdrawalPersistenceMapper.toDomain(withdrawalEntity);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getAmount()).isEqualByComparingTo("100.00");
        assertThat(result.getDate()).isEqualTo(LocalDate.of(2026, 3, 1));
    }

    @Test
    void shouldMapWithdrawalToEntity() {
        Withdrawal withdrawal = Withdrawal.builder()
                .id(2)
                .amount(BigDecimal.valueOf(50.00))
                .date(LocalDate.of(2026, 3, 5))
                .build();

        TimeDepositEntity timeDepositEntity = TimeDepositEntity.builder()
                .id(20)
                .planType("student")
                .balance(BigDecimal.valueOf(1500.00))
                .days(120)
                .build();

        WithdrawalEntity result = WithdrawalPersistenceMapper.toEntity(withdrawal, timeDepositEntity);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(2);
        assertThat(result.getTimeDeposit()).isSameAs(timeDepositEntity);
        assertThat(result.getAmount()).isEqualByComparingTo("50.00");
        assertThat(result.getDate()).isEqualTo(LocalDate.of(2026, 3, 5));
    }
}
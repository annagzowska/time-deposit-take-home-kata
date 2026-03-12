package org.ikigaidigital.adapter.in.web.mapper;

import org.ikigaidigital.adapter.in.web.dto.WithdrawalDto;
import org.ikigaidigital.domain.model.Withdrawal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class WithdrawalWebMapperTest {
    @Test
    void shouldMapWithdrawalToDto() {
        Withdrawal withdrawal = Withdrawal.builder()
                .id(1)
                .amount(BigDecimal.valueOf(250.00))
                .date(LocalDate.of(2026, 3, 10))
                .build();

        WithdrawalDto result = WithdrawalWebMapper.toDto(withdrawal);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1);
        assertThat(result.amount()).isEqualByComparingTo("250.00");
        assertThat(result.date()).isEqualTo(LocalDate.of(2026, 3, 10));
    }

}
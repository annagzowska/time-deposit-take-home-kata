package org.ikigaidigital.adapter.in.web.mapper;

import org.ikigaidigital.adapter.in.web.dto.WithdrawalDto;
import org.ikigaidigital.domain.model.Withdrawal;

public class WithdrawalWebMapper {

    private WithdrawalWebMapper() {
    }

    public static WithdrawalDto toDto(Withdrawal withdrawal) {
        return WithdrawalDto.builder()
                .id(withdrawal.getId())
                .amount(withdrawal.getAmount())
                .date(withdrawal.getDate())
                .build();
    }
}

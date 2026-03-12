package org.ikigaidigital.adapter.out.persistence.mapper;

import lombok.NoArgsConstructor;
import org.ikigaidigital.adapter.out.persistence.entity.TimeDepositEntity;
import org.ikigaidigital.adapter.out.persistence.entity.WithdrawalEntity;
import org.ikigaidigital.domain.model.Withdrawal;

@NoArgsConstructor
public class WithdrawalPersistenceMapper {

    public static Withdrawal toDomain(WithdrawalEntity withdrawalEntity) {
        return Withdrawal.builder()
                .id(withdrawalEntity.getId())
                .amount(withdrawalEntity.getAmount())
                .date(withdrawalEntity.getDate())
                .build();
    }

    public static WithdrawalEntity toEntity(Withdrawal withdrawal, TimeDepositEntity timeDepositEntity) {
        return WithdrawalEntity.builder()
                .id(withdrawal.getId())
                .timeDeposit(timeDepositEntity)
                .amount(withdrawal.getAmount())
                .date(withdrawal.getDate())
                .build();
    }
}

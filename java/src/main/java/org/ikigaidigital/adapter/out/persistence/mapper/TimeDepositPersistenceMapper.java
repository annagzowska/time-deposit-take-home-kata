package org.ikigaidigital.adapter.out.persistence.mapper;

import lombok.NoArgsConstructor;
import org.ikigaidigital.adapter.out.persistence.entity.TimeDepositEntity;
import org.ikigaidigital.adapter.out.persistence.entity.WithdrawalEntity;
import org.ikigaidigital.domain.model.PlanType;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.ikigaidigital.domain.model.Withdrawal;

import java.util.List;

@NoArgsConstructor
public class TimeDepositPersistenceMapper {

    public static TimeDepositEntity toEntity(TimeDeposit timeDeposit) {
        if (timeDeposit == null) {
            throw new IllegalArgumentException("Time deposit cannot be null");
        }
        final TimeDepositEntity timeDepositEntity = TimeDepositEntity.builder()
                .id(timeDeposit.getId())
                .planType(timeDeposit.getPlanType().name().toLowerCase())
                .balance(timeDeposit.getBalance())
                .days(timeDeposit.getDays())
                .build();
        final List<WithdrawalEntity> withdrawals = timeDeposit.getWithdrawals() == null
                ? List.of()
                : timeDeposit.getWithdrawals()
                .stream()
                .map(withdrawal -> WithdrawalPersistenceMapper.toEntity(withdrawal, timeDepositEntity))
                .toList();
        timeDepositEntity.setWithdrawals(withdrawals);
        return timeDepositEntity;
    }

    public static TimeDeposit toDomain(TimeDepositEntity entity) {
        final List<Withdrawal> withdrawals = entity.getWithdrawals().stream().map(WithdrawalPersistenceMapper::toDomain).toList();
        return new TimeDeposit(entity.getId(), PlanType.valueOf(entity.getPlanType().toUpperCase()), entity.getBalance(), entity.getDays(),withdrawals);
    }
}

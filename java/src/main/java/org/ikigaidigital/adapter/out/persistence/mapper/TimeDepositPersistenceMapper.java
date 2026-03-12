package org.ikigaidigital.adapter.out.persistence.mapper;

import lombok.NoArgsConstructor;
import org.ikigaidigital.adapter.out.persistence.entity.TimeDepositEntity;
import org.ikigaidigital.domain.PlanType;
import org.ikigaidigital.domain.TimeDeposit;

@NoArgsConstructor
public class TimeDepositPersistenceMapper {

    public static TimeDepositEntity toEntity(TimeDeposit timeDeposit) {
        return TimeDepositEntity.builder()
                .id(timeDeposit.getId())
                .planType(timeDeposit.getPlanType().name())
                .balance(timeDeposit.getBalance())
                .days(timeDeposit.getDays())
//                .withdrawals(timeDeposit.getWithdrawals())
                .build();
    }

    public static TimeDeposit toDomain(TimeDepositEntity entity) {
        return new TimeDeposit(entity.getId(), PlanType.valueOf(entity.getPlanType()), entity.getBalance(), entity.getDays());
    }
}

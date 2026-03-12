package org.ikigaidigital.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ikigaidigital.domain.PlanType;
import org.ikigaidigital.domain.TimeDeposit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "time_deposits")
@NoArgsConstructor
@Getter
public class TimeDepositEntity {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "plan_type")
    private String planType;
    private BigDecimal balance;
    private int days;
    @OneToMany(mappedBy = "timeDeposit", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<WithdrawalEntity> withdrawals = new ArrayList<>();

    public TimeDeposit toDomain() {
        return new TimeDeposit(id, PlanType.valueOf(planType), balance, days);
    }

    public TimeDepositEntity fromDomain(TimeDeposit timeDeposit) {
        this.id = timeDeposit.getId();
        this.planType = timeDeposit.getPlanType().name();
        this.balance = timeDeposit.getBalance();
        this.days = timeDeposit.getDays();
        return this;
    }
}

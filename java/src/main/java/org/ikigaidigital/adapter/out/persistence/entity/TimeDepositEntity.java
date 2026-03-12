package org.ikigaidigital.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "time_deposits")
@Builder
@AllArgsConstructor
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
    @Setter
    @OneToMany(mappedBy = "timeDeposit", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<WithdrawalEntity> withdrawals = new ArrayList<>();

}

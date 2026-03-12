package org.ikigaidigital.adapter.out.persistence.repository;

import org.ikigaidigital.adapter.out.persistence.entity.WithdrawalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataWithdrawalRepository extends JpaRepository<WithdrawalEntity, Long> {
}

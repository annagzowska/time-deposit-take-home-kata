package org.ikigaidigital.adapter.out.persistence.repository;

import org.ikigaidigital.adapter.out.persistence.entity.TimeDepositEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTimeDepositRepository extends JpaRepository<TimeDepositEntity, Long> {
}

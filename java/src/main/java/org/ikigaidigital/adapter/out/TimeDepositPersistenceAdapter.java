package org.ikigaidigital.adapter.out;

import lombok.RequiredArgsConstructor;
import org.ikigaidigital.adapter.out.persistence.entity.TimeDepositEntity;
import org.ikigaidigital.adapter.out.persistence.repository.SpringDataTimeDepositRepository;
import org.ikigaidigital.application.port.out.TimeDepositPersistencePort;
import org.ikigaidigital.domain.TimeDeposit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeDepositPersistenceAdapter implements TimeDepositPersistencePort {

    private final SpringDataTimeDepositRepository timeDepositRepository;

    @Override
    public List<TimeDeposit> findAll() {
        return timeDepositRepository.findAll()
                .stream()
                .map(TimeDepositEntity::toDomain)
                .toList();
    }

    @Override
    public TimeDeposit save(TimeDeposit timeDeposit) {
        TimeDepositEntity entity = new TimeDepositEntity().fromDomain(timeDeposit);
        TimeDepositEntity savedEntity = timeDepositRepository.save(entity);
        return savedEntity.toDomain();
    }
}

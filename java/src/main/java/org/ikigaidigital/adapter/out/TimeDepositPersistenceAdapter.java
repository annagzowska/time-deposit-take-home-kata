package org.ikigaidigital.adapter.out;

import lombok.RequiredArgsConstructor;
import org.ikigaidigital.adapter.out.persistence.entity.TimeDepositEntity;
import org.ikigaidigital.adapter.out.persistence.mapper.TimeDepositPersistenceMapper;
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
                .map(TimeDepositPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void saveAll(List<TimeDeposit> timeDeposit) {
        List<TimeDepositEntity> entities = timeDeposit.stream().map(TimeDepositPersistenceMapper::toEntity).toList();
        timeDepositRepository.saveAll(entities);
    }
}

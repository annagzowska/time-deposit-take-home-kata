package org.ikigaidigital.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.ikigaidigital.adapter.in.web.dto.TimeDepositDto;
import org.ikigaidigital.adapter.in.web.mapper.TimeDepositWebMapper;
import org.ikigaidigital.application.port.in.GetTimeDepositsUseCase;
import org.ikigaidigital.application.port.in.UpdateTimeDepositsBalanceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/time-deposits")
@RequiredArgsConstructor
public class TimeDepositController {

    private final GetTimeDepositsUseCase getTimeDepositsUseCase;
    private final UpdateTimeDepositsBalanceUseCase updateTimeDepositsBalanceUseCase;

    @GetMapping
    public ResponseEntity<List<TimeDepositDto>> getTimeDeposits() {
        List<TimeDepositDto> timeDepositDtos = getTimeDepositsUseCase.getTimeDeposits()
                .stream()
                .map(TimeDepositWebMapper::toDto)
                .toList();
        return ResponseEntity.ok(timeDepositDtos);
    }

    @PostMapping("/update-balance")
    public ResponseEntity<Void> updateTimeDepositsBalance() {
        updateTimeDepositsBalanceUseCase.updateAllTimeDepositsBalance();
        return ResponseEntity.ok().build();
    }
}

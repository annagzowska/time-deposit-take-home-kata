package org.ikigaidigital.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Time Deposits", description = "Operations for time deposits")
public class TimeDepositController {

    private final GetTimeDepositsUseCase getTimeDepositsUseCase;
    private final UpdateTimeDepositsBalanceUseCase updateTimeDepositsBalanceUseCase;

    @GetMapping
    @Operation(summary = "Get all time deposits", description = "Returns all time deposits")
    public ResponseEntity<List<TimeDepositDto>> getTimeDeposits() {
        List<TimeDepositDto> timeDeposits = getTimeDepositsUseCase.getTimeDeposits()
                .stream()
                .map(TimeDepositWebMapper::toDto)
                .toList();
        return ResponseEntity.ok(timeDeposits);
    }

    @PostMapping("/update-balance")
    @Operation(summary = "Update balances", description = "Updates balances for saved time deposits")
    public ResponseEntity<Void> updateBalancesForTimeDeposits() {
        updateTimeDepositsBalanceUseCase.updateBalancesForAllTimeDeposits();
        return ResponseEntity.ok().build();
    }
}

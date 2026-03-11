package org.ikigaidigital.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.ikigaidigital.adapter.in.web.dto.TimeDepositDto;
import org.ikigaidigital.application.port.in.GetTimeDepositsUseCase;
import org.ikigaidigital.application.port.in.UpdateTimeDepositsBalanceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        return ResponseEntity.ok(getTimeDepositsUseCase.getTimeDeposits());
    }




}

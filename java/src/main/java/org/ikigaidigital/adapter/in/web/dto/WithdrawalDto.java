package org.ikigaidigital.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Schema(description = "Withdrawal response")
public record WithdrawalDto(
        @Schema(description = "Withdrawal identifier", example = "1")
        int id,
        @Schema(description = "Withdrawal amount", example = "100.00")
        BigDecimal amount,
        @Schema(description = "Withdrawal date", example = "2026-03-01")
        LocalDate date
) {
}

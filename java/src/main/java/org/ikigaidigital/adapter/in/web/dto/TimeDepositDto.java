package org.ikigaidigital.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.ikigaidigital.domain.PlanType;

import java.math.BigDecimal;

@Schema(description = "Time deposit response")
public record TimeDepositDto(
        @Schema(description = "Time deposit identifier", example = "1")
        int id,
        @Schema(description = "Time deposit plan type", example = "BASIC")
        PlanType planType,
        @Schema(description = "Current balance", example = "1000.00")
        BigDecimal balance,
        @Schema(description = "Number of days since opening", example = "60")
        int days) {
}
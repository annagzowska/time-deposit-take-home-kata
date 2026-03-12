package org.ikigaidigital.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.ikigaidigital.domain.model.PlanType;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Schema(description = "Time deposit response")
public record TimeDepositDto(
        @Schema(description = "Time deposit identifier", example = "1")
        int id,
        @Schema(description = "Time deposit plan type", example = "BASIC")
        PlanType planType,
        @Schema(description = "Current balance", example = "1000.00")
        BigDecimal balance,
        @Schema(description = "Number of days since opening", example = "60")
        int days,
        @Schema(description = "Withdrawals assigned to the time deposit")
        List<WithdrawalDto> withdrawals
        ) {
}
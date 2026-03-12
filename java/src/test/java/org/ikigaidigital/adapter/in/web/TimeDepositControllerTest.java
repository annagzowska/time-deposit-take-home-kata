package org.ikigaidigital.adapter.in.web;

import org.ikigaidigital.application.port.in.GetTimeDepositsUseCase;
import org.ikigaidigital.application.port.in.UpdateTimeDepositsBalanceUseCase;
import org.ikigaidigital.domain.model.PlanType;
import org.ikigaidigital.domain.model.TimeDeposit;
import org.ikigaidigital.domain.model.Withdrawal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TimeDepositController.class)
@Import(OpenApiConfig.class)
class TimeDepositControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetTimeDepositsUseCase getTimeDepositsUseCase;

    @MockitoBean
    private UpdateTimeDepositsBalanceUseCase updateTimeDepositsBalanceUseCase;

    @Test
    void shouldReturnAllTimeDeposits() throws Exception {
        //given
        Withdrawal withdrawal = Withdrawal.builder()
                .id(1)
                .amount(BigDecimal.valueOf(100.00))
                .date(LocalDate.of(2026, 3, 1))
                .build();

        TimeDeposit timeDeposit = new TimeDeposit(
                1,
                PlanType.BASIC,
                BigDecimal.valueOf(1000.00),
                60,
                List.of(withdrawal)
        );

        when(getTimeDepositsUseCase.getTimeDeposits()).thenReturn(List.of(timeDeposit));

        //when & then
        mockMvc.perform(get("/time-deposits"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].planType").value("BASIC"))
                .andExpect(jsonPath("$[0].balance").value(1000.00))
                .andExpect(jsonPath("$[0].days").value(60))
                .andExpect(jsonPath("$[0].withdrawals[0].id").value(1))
                .andExpect(jsonPath("$[0].withdrawals[0].amount").value(100.00))
                .andExpect(jsonPath("$[0].withdrawals[0].date").value("2026-03-01"));

        verify(getTimeDepositsUseCase).getTimeDeposits();
    }

    @Test
    void shouldUpdateBalancesForAllTimeDeposits() throws Exception {
        //given
        doNothing().when(updateTimeDepositsBalanceUseCase).updateBalancesForAllTimeDeposits();

        //when & then
        mockMvc.perform(post("/time-deposits/update-balance"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(updateTimeDepositsBalanceUseCase).updateBalancesForAllTimeDeposits();
    }
}
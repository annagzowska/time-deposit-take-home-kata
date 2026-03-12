package org.ikigaidigital.adapter.in.web;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI timeDepositOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Time Deposit API")
                        .version("1.0.0")
                        .description("API for retrieving time deposits and updating their balances"));
    }
}

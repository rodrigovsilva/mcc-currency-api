package com.rvfs.challenge.mcc.currency;

import com.rvfs.challenge.mcc.currency.service.CurrencyConversionService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class CurrencyConversionServiceTestConfiguration {

    @Bean
    @Primary
    public CurrencyConversionService currencyConversionService() {
        return Mockito.mock(CurrencyConversionService.class);
    }
}
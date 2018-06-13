package com.rvfs.challenge.mcc.currency;

import com.rvfs.challenge.mcc.currency.dto.CurrencyConversionDTO;
import com.rvfs.challenge.mcc.currency.dto.ExchangeRateDTO;
import com.rvfs.challenge.mcc.currency.service.CurrencyConversionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MccCurrencyApiApplication.class)
public class MccCurrencyApiApplicationTests {

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @Test
    public void whenUserIdIsProvided_thenRetrievedNameIsCorrect() {
        CurrencyConversionDTO prm = new CurrencyConversionDTO();
        prm.setExchange("BRL");


        CurrencyConversionDTO result = new CurrencyConversionDTO();
        result.setExchange("BRL");
        ExchangeRateDTO rate1 = new ExchangeRateDTO();
        rate1.setExchange("USD");
        rate1.setRate(new BigDecimal("3.693"));

        List<ExchangeRateDTO> rates = new ArrayList<>();
        rates.add(rate1);
        result.setExchangeRates(rates);

        Mockito.when(currencyConversionService.getConversionRates(prm)).thenReturn(result);
        Assert.assertNotNull("Currency has exchange rates", result);
    }

}

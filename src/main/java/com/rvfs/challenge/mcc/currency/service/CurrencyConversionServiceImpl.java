package com.rvfs.challenge.mcc.currency.service;

import com.rvfs.challenge.mcc.currency.dto.CurrencyConversionDTO;
import com.rvfs.challenge.mcc.currency.dto.ExchangeRateDTO;
import com.rvfs.challenge.mcc.currency.exception.ConversionRatesException;
import com.rvfs.challenge.mcc.currency.model.CurrencyConversion;
import com.rvfs.challenge.mcc.currency.repository.CurrencyConversionRepository;
import com.rvfs.challenge.mcc.currency.service.dto.currencyconverter.ConversionRates;
import com.rvfs.challenge.mcc.currency.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of all currency converter services.
 */
@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

    @Autowired
    private Environment env;

    @Autowired
    private CurrencyConversionRepository currencyConversionRepository;

    @Override
    public CurrencyConversionDTO getConversionRates(CurrencyConversionDTO CurrencyConversionDTO) {

        MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<>();
        uriVariables.add("access_key", env.getProperty(Constants.CURRENCY_API_KEY));
        //uriVariables.add("currencies", target.getCode());
        uriVariables.add("source", CurrencyConversionDTO.getExchange());
        uriVariables.add("format", "1");

        //http://www.mocky.io/v2/5b199e6d3000005a00da17c7
        /*UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(env.getProperty(Constants.CURRENCY_API_BASE_URL))
                .queryParams(uriVariables).build();*/

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("http://www.mocky.io/v2/5b21603d30000090265c7469")
                .queryParams(uriVariables).build();

        RestTemplate restTemplate = new RestTemplate();
        ConversionRates conversionRates = restTemplate.getForObject(uriComponents.toUri(), ConversionRates.class);

        if (conversionRates.getSuccess()) {
            Calendar timestamp = Calendar.getInstance();
            timestamp.setTimeInMillis(conversionRates.getTimestamp());
            CurrencyConversionDTO.setTimestamp(timestamp);

            if (conversionRates.getQuotes() != null) {
                List<ExchangeRateDTO> exchangeRates = conversionRates.getQuotes().entrySet().stream()
                        .map(e -> new ExchangeRateDTO(
                                StringUtils.substringAfter(e.getKey(), conversionRates.getSource()), e.getValue()))
                        .collect(Collectors.toList());
                CurrencyConversionDTO.setExchangeRates(exchangeRates);
            }

        } else {
            throw new ConversionRatesException(conversionRates.getError().getInfo());
        }

        return CurrencyConversionDTO;
    }

    @Override
    public List<CurrencyConversion> getHistoricalCurrencyConversions(int listSize) {
        Pageable pageable = new PageRequest(0, listSize, Sort.Direction.DESC, "timestamp");

        Page<CurrencyConversion> currencyConversions = currencyConversionRepository.findAll(pageable);

        // this is a list of the last 10 records, you can choose to invert it by using
        //List<CurrencyConversion> bottomUsersList = bottomPage.getContent();

        //Collections.inverse(currencyConversions);
        return currencyConversions.getContent();
    }
}

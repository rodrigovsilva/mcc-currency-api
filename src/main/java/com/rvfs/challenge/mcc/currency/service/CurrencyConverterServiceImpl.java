package com.rvfs.challenge.mcc.currency.service;

import com.rvfs.challenge.mcc.currency.dto.ConversionDTO;
import com.rvfs.challenge.mcc.currency.dto.ExchangeRateDTO;
import com.rvfs.challenge.mcc.currency.exception.ConversionRatesException;
import com.rvfs.challenge.mcc.currency.model.ConversionRates;
import com.rvfs.challenge.mcc.currency.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of all currency converter services.
 */
@Service
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    @Autowired
    private Environment env;

    @Override
    public ConversionDTO getConversionRates(ConversionDTO conversionDTO) {

        MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<>();
        uriVariables.add("access_key", env.getProperty(Constants.CURRENCY_API_KEY));
        //uriVariables.add("currencies", target.getCode());
        uriVariables.add("source", conversionDTO.getExchange());
        uriVariables.add("format", "1");

        //http://www.mocky.io/v2/5b199e6d3000005a00da17c7
        /*UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(env.getProperty(Constants.CURRENCY_API_BASE_URL))
                .queryParams(uriVariables).build();*/

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("http://www.mocky.io/v2/5b199e6d3000005a00da17c7")
                .queryParams(uriVariables).build();

        RestTemplate restTemplate = new RestTemplate();
        ConversionRates conversionRates = restTemplate.getForObject(uriComponents.toUri(), ConversionRates.class);

        if (conversionRates.getSuccess()) {
            Calendar timestamp = Calendar.getInstance();
            timestamp.setTimeInMillis(conversionRates.getTimestamp());
            conversionDTO.setTimestamp(timestamp);

            if (conversionRates.getQuotes() != null) {
                List<ExchangeRateDTO> exchangeRates = conversionRates.getQuotes().entrySet().stream()
                        .map(e -> new ExchangeRateDTO(
                                StringUtils.substringAfter(e.getKey(), conversionRates.getSource()), e.getValue()))
                        .collect(Collectors.toList());
                conversionDTO.setExchangeRates(exchangeRates);
            }

        } else {
            throw new ConversionRatesException(conversionRates.getError().getInfo());
        }

        return conversionDTO;
    }
}

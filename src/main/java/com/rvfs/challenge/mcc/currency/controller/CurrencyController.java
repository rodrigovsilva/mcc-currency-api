package com.rvfs.challenge.mcc.currency.controller;

import com.rvfs.challenge.mcc.currency.dto.ConversionDTO;
import com.rvfs.challenge.mcc.currency.service.CurrencyConverterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("currency")
public class CurrencyController {

    /**
     * Logger definition.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    CurrencyConverterService currencyConverterService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/convert")
    public ResponseEntity<Object> convert() {

        ResponseEntity<Object> responseEntity = null;

        try {
            ConversionDTO conversionDTO = new ConversionDTO();
            conversionDTO.setExchange("BRL");
            //responseEntity = new ResponseEntity<>(currencyConverterService.getConversionRates(conversionDTO), HttpStatus.OK);
            responseEntity = new ResponseEntity<>("OK 2", HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            //responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            responseEntity = new ResponseEntity<>("Ocorreu um erro ao acessar a operação.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }
}

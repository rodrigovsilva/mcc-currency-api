package com.rvfs.challenge.mcc.currency.service;

import com.rvfs.challenge.mcc.currency.dto.CurrencyConversionDTO;
import com.rvfs.challenge.mcc.currency.model.CurrencyConversion;

import java.util.List;

/**
 * Converter services interface.
 */
public interface CurrencyConversionService {

    /**
     * Get the conversion rates of a currency.
     *
     * @param conversion CurrencyConversion requested data.
     * @return CurrencyConversion with all available exchange rates.
     */
    public CurrencyConversionDTO getConversionRates(CurrencyConversionDTO conversion);

    /**
     * Find all Currency Conversions.
     *
     * @param listSize Size of historical currency conversions.
     * @return List of currency conversions.
     */
    public List<CurrencyConversion> getHistoricalCurrencyConversions(int listSize);
}

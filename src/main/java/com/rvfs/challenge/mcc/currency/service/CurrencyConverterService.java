package com.rvfs.challenge.mcc.currency.service;

import com.rvfs.challenge.mcc.currency.dto.ConversionDTO;

/**
 * Converter services interface.
 */
public interface CurrencyConverterService {

    /**
     * Get the conversion rates of a currency
     * @param conversion Conversion requested data.
     * @return Conversion with all available exchange rates.
     */
    public ConversionDTO getConversionRates(ConversionDTO conversion);
}

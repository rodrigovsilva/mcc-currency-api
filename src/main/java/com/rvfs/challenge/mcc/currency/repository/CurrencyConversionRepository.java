package com.rvfs.challenge.mcc.currency.repository;

import com.rvfs.challenge.mcc.currency.model.CurrencyConversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyConversionRepository extends CrudRepository<CurrencyConversion, Long> {

    public Page<CurrencyConversion> findAll(Pageable page);

}

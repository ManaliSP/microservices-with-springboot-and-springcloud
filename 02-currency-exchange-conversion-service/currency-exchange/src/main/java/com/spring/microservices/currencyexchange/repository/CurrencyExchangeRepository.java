package com.spring.microservices.currencyexchange.repository;

import com.spring.microservices.currencyexchange.request.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    public CurrencyExchange findByFromCurrencyAndToCurrency(String from, String to);
}

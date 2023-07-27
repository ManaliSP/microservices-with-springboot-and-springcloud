package com.spring.microservices.currencyexchange.controller;

import com.spring.microservices.currencyexchange.repository.CurrencyExchangeRepository;
import com.spring.microservices.currencyexchange.request.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String fromCurrency, @PathVariable String toCurrency){

        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);

        if (currencyExchange == null){
            throw new RuntimeException("Unable to find data for : " + fromCurrency + " to " + toCurrency);
        }

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }
}

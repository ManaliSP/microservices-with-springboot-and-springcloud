package com.spring.microservices.currencyexchange.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
//    @CircuitBreaker(name = "sample-api", fallbackMethod = "hardcodedResponse")
//    @RateLimiter(name = "default")      // 10s => 1000 calls to the sample-spi
    @Bulkhead(name = "sample-api")
    public String sampleApi(){

        logger.info("sample api call received.");
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return responseEntity.getBody();
    }

    public String hardcodedResponse(Exception exception){
        return "fallback-response";
    }
}

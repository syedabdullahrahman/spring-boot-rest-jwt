package com.learning.rest.api.services;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ExchangeRateApiServiceTest {

    @Autowired
    private ExchangeRateApiService exchangeRateApiService = new ExchangeRateApiService();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getCurrencyRatesByCurrencyCode() {
        System.out.println(exchangeRateApiService.getCurrencyRateByCurrencyCode("BDT"));
    }
}
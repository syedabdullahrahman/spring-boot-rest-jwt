package com.assignment.rest.api.services;

import com.assignment.rest.exchangeaoi.response.CurrencyRates;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateApiService {
    private static final String BASE_PATH = "https://open.er-api.com/v6/latest";
    private RestTemplate restTemplate = new RestTemplate();

    public CurrencyRates getCurrencyRatesByCurrencyCode(String currencyCode){
        CurrencyRates deatils = restTemplate.getForObject(BASE_PATH+"/"+currencyCode, CurrencyRates.class);
        return deatils;
    }
}

package com.learning.rest.api.services;

import com.learning.rest.exchangeaoi.response.CurrencyRate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateApiService {
    private static final String BASE_PATH = "https://open.er-api.com/v6/latest";
    private RestTemplate restTemplate = new RestTemplate();

    public CurrencyRate getCurrencyRateByCurrencyCode(String currencyCode){
        CurrencyRate details = restTemplate.getForObject(BASE_PATH+"/"+currencyCode, CurrencyRate.class);
        ///restTemplate.
        return details;
    }
}

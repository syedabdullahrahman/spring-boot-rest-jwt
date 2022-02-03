package com.learning.service;

import com.learning.dto.v1.CountryDetailV1;
import com.learning.rest.api.services.ExchangeRateApiService;
import com.learning.rest.api.services.RestCountriesApiService;
import com.learning.rest.countryapi.response.Country;
import com.learning.rest.countryapi.response.Currency;
import com.learning.rest.exchangeaoi.response.CurrencyRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{

    @Autowired
    private ExchangeRateApiService exchangeRateApiService;
    @Autowired
    private RestCountriesApiService restCountriesApiService;

    @Override
    public List<CountryDetailV1> getDetailsByName(String partialCountryName) throws Exception {
        List<CountryDetailV1> countryDetailV1s = new ArrayList<>();
        for (Country countryDetailsByName : restCountriesApiService.getCountriesByPartialName(partialCountryName)) {
            CountryDetailV1 countryDetailV1 = new CountryDetailV1();
            countryDetailV1.setFullName(countryDetailsByName.getFullName());
            countryDetailV1.setPopulation(countryDetailsByName.getPopulation());
            countryDetailV1.setOfficialCurrenciesWithEmptyRates(countryDetailsByName.getCurrencies());

            for (Currency currency: countryDetailsByName.getCurrencies()) {
                CurrencyRate currencyRatesByCurrencyCode = exchangeRateApiService.getCurrencyRateByCurrencyCode(currency.getCode());
                countryDetailV1.setOfficialCurrenciesINRRate(currency.getCode(),currencyRatesByCurrencyCode.getINRRate());
            }
            countryDetailV1s.add(countryDetailV1);
        }
        return countryDetailV1s;
    }
}

package com.assignment.service;

import com.assignment.dto.CountryDetail;
import com.assignment.rest.api.services.ExchangeRateApiService;
import com.assignment.rest.api.services.RestCountriesApiService;
import com.assignment.rest.countryapi.response.Country;
import com.assignment.rest.countryapi.response.Currency;
import com.assignment.rest.exchangeaoi.response.CurrencyRates;
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
    public List<CountryDetail> getDetailsByName(String partialCountryName) throws Exception {
        List<CountryDetail> countryDetails = new ArrayList<>();
        for (Country countryDetailsByName : restCountriesApiService.getCountryDetailsByName(partialCountryName)) {
            CountryDetail countryDetail = new CountryDetail();
            countryDetail.setFullName(countryDetailsByName.getName());
            countryDetail.setPopulation(countryDetailsByName.getPopulation());
            countryDetail.setOfficialCurrenciesWithEmptyRates(countryDetailsByName.getCurrencies());

            for (Currency currency: countryDetailsByName.getCurrencies()) {
                CurrencyRates currencyRatesByCurrencyCode = exchangeRateApiService.getCurrencyRatesByCurrencyCode(currency.getCode());
                countryDetail.setOfficialCurrenciesINRRate(currency.getCode(),currencyRatesByCurrencyCode.getINRRate());
            }
            countryDetails.add(countryDetail);
        }
        return countryDetails;
    }
}

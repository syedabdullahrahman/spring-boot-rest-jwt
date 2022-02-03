package com.learning.dto.v2;

import com.learning.rest.countryapi.response.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CountryDetailV2 {
    private String fullName;
    private Long population;
    private Map<String,BigDecimal > officialCurrencies = new ConcurrentHashMap<>();

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Map<String, BigDecimal> getOfficialCurrencies() {
        return officialCurrencies;
    }

    public void setOfficialCurrenciesWithEmptyRates(List<Currency> currencies) {
        this.officialCurrencies.clear();
        currencies.forEach(currency -> this.officialCurrencies.put(currency.getCode(),BigDecimal.ZERO));
    }

    public void setOfficialCurrenciesINRRate(String currencyCode, BigDecimal inrRate) throws Exception {
        if(fullName==null || fullName.isBlank()) throw new Exception("Country Name can't be empty");

        if(this.officialCurrencies.containsKey(currencyCode)){
            this.officialCurrencies.put(currencyCode,inrRate );
        } else {
            throw new Exception(fullName+" doesn't have "+currencyCode+" in official currencies.");
        }
    }
}

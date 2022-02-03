package com.learning.rest.countryapi.response;

import java.util.List;
import java.util.Optional;

public class Country {
    private String name;
    private List<Currency> currencies;
    private Long population;
    private List<String> altSpellings;

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        Optional<String> countryFullName = altSpellings.stream().filter(name -> name.contains(this.name)).findFirst();
        if(countryFullName.isPresent()){
            return countryFullName.get();
        }else {
            return this.name;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}

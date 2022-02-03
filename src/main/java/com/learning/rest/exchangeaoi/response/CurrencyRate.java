package com.learning.rest.exchangeaoi.response;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CurrencyRate {
    private String base_code;
    private Map<String, BigDecimal> rates= new ConcurrentHashMap<>();

    public CurrencyRate() {
    }

    public CurrencyRate(String base_code, Map<String, BigDecimal> rates) {
        this.base_code = base_code;
        this.rates = rates;
    }

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }

    public BigDecimal getINRRate() throws Exception {
        if(rates.containsKey("INR")){
            return rates.get("INR");
        }else {
            throw new Exception(base_code.toUpperCase()+" -> INR rates not found.");
        }
    }
}

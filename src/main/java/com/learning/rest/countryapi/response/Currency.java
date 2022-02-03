package com.learning.rest.countryapi.response;

public class Currency {
    private String code;
    private String name;
    private String symbol;

    public String getCode() {
        return code.toUpperCase();
    }

    public void setCode(String code) {
        this.code = code.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}

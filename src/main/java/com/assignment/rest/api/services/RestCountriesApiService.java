package com.assignment.rest.api.services;

import com.assignment.rest.countryapi.response.Country;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestCountriesApiService {
    private static final String BASE_PATH = "https://restcountries.com/v2";
    private RestTemplate restTemplate = new RestTemplate();

    public List<Country> getCountryDetailsByName(String name){
        Country[] deatils = restTemplate.getForObject(BASE_PATH+"/name/"+name, Country[].class);
        return Arrays.asList(deatils);
    }
}

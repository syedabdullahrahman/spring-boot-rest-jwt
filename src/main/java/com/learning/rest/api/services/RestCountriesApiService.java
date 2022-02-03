package com.learning.rest.api.services;

import com.learning.rest.countryapi.response.Country;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestCountriesApiService {
    private static final String BASE_PATH = "https://restcountries.com/v2";
    private RestTemplate restTemplate = new RestTemplate();

    public List<Country> getCountriesByPartialName(String partialName){
        Country[] details = restTemplate.getForObject(BASE_PATH+"/name/"+partialName, Country[].class);
        return Arrays.asList(details);
    }
}

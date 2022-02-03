package com.learning.remote.service;

import com.learning.rest.api.services.RestCountriesApiService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestCountriesApiServiceTest {
    @Autowired
    private RestCountriesApiService restCountriesApiService = new RestCountriesApiService();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getCountryDetailsByName() {

        System.out.println(restCountriesApiService.getCountriesByPartialName("ban"));
    }
}
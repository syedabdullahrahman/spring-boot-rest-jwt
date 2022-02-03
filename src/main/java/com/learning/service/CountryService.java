package com.learning.service;

import com.learning.dto.v1.CountryDetailV1;

import java.util.List;

public interface CountryService {
    List<CountryDetailV1> getDetailsByName(String name) throws Exception;
}

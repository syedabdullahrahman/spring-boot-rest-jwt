package com.assignment.service;

import com.assignment.dto.v1.CountryDetailV1;

import java.util.List;

public interface CountryService {
    List<CountryDetailV1> getDetailsByName(String name) throws Exception;
}

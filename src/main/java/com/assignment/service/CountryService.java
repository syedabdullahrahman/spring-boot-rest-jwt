package com.assignment.service;

import com.assignment.dto.CountryDetail;

import java.util.List;

public interface CountryService {
    List<CountryDetail> getDetailsByName(String name) throws Exception;
}

package com.learning.controller.api.v2;

import com.learning.dto.v1.CountryDetailV1;
import com.learning.service.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin()
@RequestMapping("api/v2")
public class CountryControllerV2 {
    @Autowired
    private CountryServiceImpl countryService;

    @GetMapping("/name/{name}")
    public List<CountryDetailV1> getByName(@PathVariable(name = "name") Optional<String> name) throws Exception {
        if(name.isPresent()){
            return countryService.getDetailsByName(name.get());
        } else {
            return Collections.singletonList(new CountryDetailV1());
        }
    }
}

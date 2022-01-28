package com.assignment.controller.api.v1;

import com.assignment.dto.CountryDetail;
import com.assignment.service.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin()
@RequestMapping("api/v1")
public class CountryController {
    @Autowired
    private CountryServiceImpl countryService;

    @GetMapping("/name/{name}")
    public List<CountryDetail> getByName(@PathVariable(name = "name") Optional<String> name) throws Exception {
        if(name.isPresent()){
            return countryService.getDetailsByName(name.get());
        } else {
            return Collections.singletonList(new CountryDetail());
        }
    }
}

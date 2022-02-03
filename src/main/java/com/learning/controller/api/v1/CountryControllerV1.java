package com.learning.controller.api.v1;

import com.learning.dto.v1.CountryDetailV1;
import com.learning.service.CountryServiceImpl;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Collections;
import java.util.Optional;

@RestController
@CrossOrigin()
@RequestMapping("api/v1")
public class CountryControllerV1 {
    @Autowired
    private CountryServiceImpl countryService;

    private final Bucket bucket;

    public CountryControllerV1() {
        Bandwidth limit = Bandwidth.classic(1, Refill.greedy(1, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getByName(@PathVariable(name = "name") Optional<String> name) throws Exception {

        if (bucket.tryConsume(1)) {
            if(name.isPresent()){
                return ResponseEntity.ok(countryService.getDetailsByName(name.get()));
            } else {
                return ResponseEntity.ok(Collections.singletonList(new CountryDetailV1()));
            }
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}

package com.task.countries.service;

import com.task.countries.response.CountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
public class CountryRestService {
    private final RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(CountryResponse.class);
    String url = "https://restcountries.com/v2/regionalbloc/eu";

    public CountryRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CountryResponse> getAllData() {
        CountryResponse[] countries = new CountryResponse[0];
        try {
            ResponseEntity<CountryResponse[]> response = restTemplate.getForEntity(url, CountryResponse[].class);
            if (response.getBody() != null) {
                countries = response.getBody();
            }
        } catch (Exception e) {
            logger.error("Could not retrieve countries", e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return Arrays.asList(countries);
    }

}



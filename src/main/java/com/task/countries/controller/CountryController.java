package com.task.countries.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.task.countries.response.CountryResponse;
import com.task.countries.service.CountryService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<byte[]> getAllData() throws JsonProcessingException {
        return countryService.getAllData();
    }

    @PostMapping("/import-biggest-population")
    public List<CountryResponse> importBiggestPopulation(@RequestParam("file") MultipartFile file) throws IOException {
        return countryService.importBiggestPopulation(file);
    }

    @PostMapping("/import-biggest-area")
    public List<CountryResponse> importBiggestArea(@RequestParam("file") MultipartFile file) throws IOException {
        return countryService.importBiggestArea(file);
    }

    @PostMapping("/import-biggest-density")
    public List<CountryResponse> importBiggestDensity(@RequestParam("file") MultipartFile file) throws IOException {
        return countryService.importBiggestDensity(file);
    }


    @GetMapping("/get-biggest-population")
    public List<CountryResponse> getBiggestPopulation() {
        return countryService.getBiggestPopulation();
    }

    @GetMapping("/get-biggest-area")
    public List<CountryResponse> getBiggestArea() {
        return countryService.getBiggestArea();
    }

    @GetMapping("/get-biggest-density")
    public List<CountryResponse> getBiggestDensity() {
        return countryService.getBiggestDensity();
    }
}

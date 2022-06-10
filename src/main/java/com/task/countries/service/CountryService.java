package com.task.countries.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.countries.response.CountryResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRestService countryRestService;

    public CountryService(CountryRestService countryRestService) {
        this.countryRestService = countryRestService;
    }

    public ResponseEntity<byte[]> getAllData() throws JsonProcessingException {
        List<CountryResponse> countriesList = countryRestService.getAllData();
        ObjectMapper mapper = new ObjectMapper();
        byte[] countries = mapper.writeValueAsBytes(countriesList);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=countries.json")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(countries.length)
                .body(countries);
    }

    //Imports
    public List<CountryResponse> importBiggestPopulation(MultipartFile file) throws IOException {
        parse(file);
        return getTop10(Comparator.comparing(CountryResponse::getPopulation).reversed());
    }

    public List<CountryResponse> importBiggestArea(MultipartFile file) throws IOException {
        parse(file);
        return getTop10(Comparator.comparing(CountryResponse::getArea).reversed());
    }

    public List<CountryResponse> importBiggestDensity(MultipartFile file) throws IOException {
        parse(file);
        return getTop10(Comparator.comparing(CountryResponse::calculateDensity).reversed());
    }

    // Get from REST API
    public List<CountryResponse> getBiggestPopulation() {
        return getTop10(Comparator.comparing(CountryResponse::getPopulation).reversed());
    }

    public List<CountryResponse> getBiggestArea() {
        return getTop10(Comparator.comparing(CountryResponse::getArea).reversed());
    }

    public List<CountryResponse> getBiggestDensity() {
        return getTop10(Comparator.comparing(CountryResponse::calculateDensity).reversed());
    }

    private List<CountryResponse> getTop10(Comparator<CountryResponse> comparator) {
        List<CountryResponse> countries = countryRestService.getAllData();
        return countries
                .stream()
                .sorted(comparator)
                .limit(10)
                .collect(Collectors.toList());
    }

    private void parse(MultipartFile file) throws IOException {
        // MultipartFile --> String
        String content = new String(file.getBytes());

        // String to Java object
        ObjectMapper objectMapper = new ObjectMapper();
        List<CountryResponse> countries = objectMapper
                .readValue(content, objectMapper.getTypeFactory().constructCollectionType(List.class, CountryResponse.class));
    }



}

package com.task.countries.response;

import java.util.List;

public class CountryResponse {

    private String name;
    private String capital;
    private List<Currency> currencies;
    private long population;
    private long area;

    public CountryResponse(String name, String capital, List<Currency> currencies, long population, long area) {
        this.name = name;
        this.capital = capital;
        this.currencies = currencies;
        this.population = population;
        this.area = area;
    }

    public CountryResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public long calculateDensity() {
        if (getPopulation() != 0 && getArea() != 0) {
            return getPopulation() / getArea();
        } else
            return 0;

    }

    @Override
    public String toString() {
        return "CountryResponse{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", currencies=" + currencies +
                ", population=" + population +
                ", area=" + area +
                '}';
    }
}

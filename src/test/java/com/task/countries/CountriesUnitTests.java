package com.task.countries;

import com.task.countries.response.CountryResponse;
import com.task.countries.response.Currency;
import com.task.countries.service.CountryRestService;
import com.task.countries.service.CountryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class CountriesUnitTests {

	@Mock
	CountryRestService countryRestService;

	@InjectMocks
	CountryService countryService;

	@Test
	public void successfulTop10Population() {

		List<CountryResponse> listOfCountries = new ArrayList<>();

		CountryResponse anotherCountry = createCountry("Latvia", "EUR",  1, 312312);

		listOfCountries.add(createCountry("Germany", "EUR", 11, 421431));
		listOfCountries.add(createCountry("France", "EUR",  10, 3125));
		listOfCountries.add(createCountry("Italy", "EUR",  9, 6546));
		listOfCountries.add(createCountry("Spain", "EUR",  8, 8867));
		listOfCountries.add(createCountry("Poland", "PLN",  7, 133));
		listOfCountries.add(createCountry("Romania", "RON",  6, 6457));
		listOfCountries.add(createCountry("Netherlands", "EUR",  5, 3213));
		listOfCountries.add(createCountry("Belgium", "EUR",  4, 5336));
		listOfCountries.add(createCountry("Greece", "EUR",  3, 432));
		listOfCountries.add(createCountry("Czech Republic", "CZK",  2, 7657));
		listOfCountries.add(anotherCountry);

		Mockito.when(countryRestService.getAllData()).thenReturn(listOfCountries);

		List<CountryResponse> countries = countryService.getBiggestPopulation();

		// check whether expected list size is the same as countries list

		Assertions.assertEquals(10, countries.size());

		// check whether Latvia is in list or not

		boolean inListLatvia = countries.contains(anotherCountry);
		Assertions.assertFalse(inListLatvia);

	}

	@Test
	public void successfulTop10Area() {

		List<CountryResponse> listOfCountries = new ArrayList<>();

		CountryResponse anotherCountry = createCountry("Latvia", "EUR",  1213123, 1);

		listOfCountries.add(createCountry("Germany", "EUR", 32145, 11));
		listOfCountries.add(createCountry("France", "EUR",  2142315, 10));
		listOfCountries.add(createCountry("Italy", "EUR",  515252, 9));
		listOfCountries.add(createCountry("Spain", "EUR",  12312512, 8));
		listOfCountries.add(createCountry("Poland", "PLN",  546567, 7));
		listOfCountries.add(createCountry("Romania", "RON",  756765, 6));
		listOfCountries.add(createCountry("Netherlands", "EUR",  341577, 5));
		listOfCountries.add(createCountry("Belgium", "EUR",  423665, 4));
		listOfCountries.add(createCountry("Greece", "EUR",  53457, 3));
		listOfCountries.add(createCountry("Czech Republic", "CZK",  425665, 2));
		listOfCountries.add(anotherCountry);

		Mockito.when(countryRestService.getAllData()).thenReturn(listOfCountries);

		List<CountryResponse> countries = countryService.getBiggestArea();

		// check whether expected list size is the same as countries list

		Assertions.assertEquals(10, countries.size());

		// check whether Latvia is in list or not

		boolean inListLatvia = countries.contains(anotherCountry);
		Assertions.assertFalse(inListLatvia);

	}

	@Test
	public void successfulTop10Density() {

		List<CountryResponse> listOfCountries = new ArrayList<>();

		CountryResponse anotherCountry = createCountry("Latvia", "EUR",  1207361, 9251);

		listOfCountries.add(createCountry("Gibraltar", "GIP", 33691, 6));
		listOfCountries.add(createCountry("Malta", "EUR",  525285, 316));
		listOfCountries.add(createCountry("Netherlands", "EUR",  17441139, 41850));
		listOfCountries.add(createCountry("Belgium", "EUR",  11555997, 30528));
		listOfCountries.add(createCountry("Luxembourg", "EUR",  632275, 2586));
		listOfCountries.add(createCountry("Germany", "EUR",  83240525, 357114));
		listOfCountries.add(createCountry("Italy", "EUR",  59554023, 301336));
		listOfCountries.add(createCountry("Isle of Man", "GBP",  85032, 572));
		listOfCountries.add(createCountry("Czech Republic", "CZK",  10698896, 78865));
		listOfCountries.add(createCountry("Denmark", "DKK",  5831404, 43094));
		listOfCountries.add(anotherCountry);

		Mockito.when(countryRestService.getAllData()).thenReturn(listOfCountries);

		List<CountryResponse> countries = countryService.getBiggestDensity();

		// check whether expected list size is the same as countries list

		Assertions.assertEquals(10, countries.size());

		// check whether Latvia is in list or not

		boolean inListLatvia = countries.contains(anotherCountry);
		Assertions.assertFalse(inListLatvia);

	}


	private CountryResponse createCountry(String name, String currency, long population, long area) {
		return new CountryResponse(name, "", List.of(new Currency(currency, "", "")), population, area);
	}


}

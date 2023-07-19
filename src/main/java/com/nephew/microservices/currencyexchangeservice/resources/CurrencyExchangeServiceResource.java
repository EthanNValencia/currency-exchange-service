package com.nephew.microservices.currencyexchangeservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nephew.microservices.currencyexchangeservice.entities.Rate;
import com.nephew.microservices.currencyexchangeservice.repository.RateRepository;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CurrencyExchangeServiceResource {

	@Autowired
	private Environment environment;
	@Autowired
	private RateRepository rateRepository;

	/***
	 * 
	 * Example URL: http://localhost:8000/currency-exchange/from/USD/to/INR
	 * 
	 * @param from Specifies the base currency type.
	 * @param to   Specifies what the solution currency type will be.
	 * @return The object that contains all the necessary data for the currency
	 *         conversion.
	 */
	@GetMapping(path = "currency-exchange/from/{from}/to/{to}")
	@Retry(name="currency-exchange-api") // check app.props
	public Rate retrieveCurrentExchangeRate(@PathVariable String from, @PathVariable String to) {
		// TODO: implement logic to retrieve currency rates
		Rate rate = rateRepository.findByFromAndTo(from, to);

		if (rate == null) {
			throw new RuntimeException("Unable to find data from " + from + " to " + to);
		}

		String port = environment.getProperty("local.server.port");
		rate.setEnvironment(port);
		// return new Rate(10001l, from, to, BigDecimal.valueOf(65.00), port,
		// LocalDate.now());
		return rate;
	}

}

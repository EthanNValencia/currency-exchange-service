package com.nephew.microservices.currencyexchangeservice.resources;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nephew.microservices.currencyexchangeservice.beans.Rate;

@RestController
public class CurrencyExchangeServiceResource {

	@Autowired
	private Environment environment;
	
	/***
	 * 
	 * Example URL: http://localhost:8000/currency-exchange/from/USD/to/INR
	 * @param from Specifies the base currency type. 
	 * @param to Specifies what the solution currency type will be. 
	 * @return The object that contains all the necessary data for the currency conversion. 
	 */
	@GetMapping(path = "currency-exchange/from/{from}/to/{to}")
	public Rate retrieveCurrentExchangeRate(@PathVariable String from, @PathVariable String to) {
		// TODO: implement logic to retrieve currency rates
		String port = environment.getProperty("local.server.port");
		return new Rate(10001l, from, to, BigDecimal.valueOf(65.00), port, LocalDate.now());
	}
	
}

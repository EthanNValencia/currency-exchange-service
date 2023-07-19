package com.nephew.microservices.currencyexchangeservice.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerResource {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerResource.class);
	
	// http://localhost:8000/sample-api
	@GetMapping(path="/sample-api")
	@Retry(name="sample-api", fallbackMethod="hardcodedResponse") // The name corresponds to a prop in app.props. 
	public String sampleApi() {
		logger.info("Sample API call recieved."); // This will be logged 5 times
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class); // This is meant to fail. 
		return forEntity.getBody();
	}
	
	public String hardcodedResponse(Exception ex) {
		return "fallback-response";
	}
	
}

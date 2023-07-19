package com.nephew.microservices.currencyexchangeservice.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;


// When testing this it is best to run the naming-server. Not necessary but it will minimize log pollution. 
@RestController
public class CircuitBreakerResource {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerResource.class);
	
	// http://localhost:8000/sample-api
	@GetMapping(path="/sample-api")
	// @Retry(name="sample-api", fallbackMethod="hardcodedResponse") // The name corresponds to a props in app.props. 
	/*
	 * In the event of a microservice being down (open state) the 
	 * CircuitBreaker will prevent requests from being executed, because 
	 * that would just add pointless load, instead it will return a default 
	 * response back. 
	 * 
	 * A circuit breaker has 3 states: closed, open, and half open
	 * 
	 * In a closed state there is a method call every time.
	 * In a half open state there is a method call a percentage of the time.
	 * In an open state no method calls are being made.  
	 * 
	 * Circuit Breaker Operation Example:
	 * Application start -> closed -> 100/100 successful requests -> closed 
	 * -> 51/100 unsuccessful requests -> open -> duration of time (gives time for the problem to be resolved)
	 * -> half open -> 100/100 successful requests -> closed
	 * 
	 * Note: The circuit breaker will even respond to method calls that take
	 * too much time to fulfill. Default is 60 seconds. 
	 */
	@CircuitBreaker(name="sample-api", fallbackMethod="hardcodedResponse")
	/*
	 * Rate Limiter is used to limit the number of api calls by time. 
	 * Ex: Limit to 10000 calls every 10 seconds. 
	 */
	public String sampleApi() {
		logger.info("Sample API call recieved."); // This will be logged 5 times
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class); // This is meant to fail. 
		return forEntity.getBody();
	}
	
	public String hardcodedResponse(Exception ex) {
		return "fallback-response";
	}
	
	// http://localhost:8000/sample-rate-limiter
	// In the app.props this is limited to 2 calls every 10 seconds.
	@GetMapping(path="/sample-rate-limiter")
	@RateLimiter(name="default")
	public String sampleRateLimiter() {
		return "Hello World!";
	}
	
	// http://localhost:8000/sample-bulkhead
	// The BulkHead limits the maximum number of concurrent api calls. 
	@GetMapping(path="/sample-bulkhead")
	@Bulkhead(name="default")
	public String sampleBulkHead() {
		return "Hello World!";
	}
	
	
	
	
}

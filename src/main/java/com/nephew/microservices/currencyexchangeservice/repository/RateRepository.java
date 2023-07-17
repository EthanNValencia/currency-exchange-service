package com.nephew.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nephew.microservices.currencyexchangeservice.entities.Rate;

public interface RateRepository extends JpaRepository<Rate, Long> {
	Rate findByFromAndTo(String from, String to);
}

package com.nephew.microservices.currencyexchangeservice.beans;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Rate {

	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionMultiple;
	private String environment;
	private LocalDate date;

	public Rate(Long id, String from, String to, BigDecimal conversionMultiple, String environment, LocalDate date) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
		this.environment = environment;
		this.date = date;
	}

	public Rate() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getConvertToMultiple() {
		return conversionMultiple;
	}

	public void setConvertToMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Rate [id=" + id + ", from=" + from + ", to=" + to + ", convertToMultiple=" + conversionMultiple
				+ ", environment=" + environment + ", date=" + date + "]";
	}

}

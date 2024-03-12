package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.projections.SaleReportProjection;

public class SaleReportDTO {
	
	private Long id;
	private LocalDate date;
	private Double amount;
	private String name;
	
	public SaleReportDTO() {
		
	}

	public SaleReportDTO(Long id, LocalDate date, Double amount, String name) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.name = name;
	}
	
	public SaleReportDTO(SaleReportProjection projection) {
		id = projection.getId();
		date = projection.getDate();
		amount = projection.getAmount();
		name = projection.getName();
	}

	public Long getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public Double getAmount() {
		return amount;
	}

	public String getName() {
		return name;
	}
	
	
}

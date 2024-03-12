package com.devsuperior.dsmeta.dto;

public class SaleSumaryDTO {
	
	private String name;
	private Double sum;
	
	public SaleSumaryDTO() {
		// TODO Auto-generated constructor stub
	}

	public SaleSumaryDTO(String name, Double sum) {
		super();
		this.name = name;
		this.sum = sum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}
	
	

}

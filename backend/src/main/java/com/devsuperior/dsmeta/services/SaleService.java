package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSumaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleReportProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		
		LocalDate endDate = getMaxDate(maxDate);
		LocalDate initDate = getMinDate(minDate, endDate);
		
		Page<SaleReportProjection> projections = repository.searchReport(initDate, endDate, name, pageable);
		
		return projections.map(x -> new SaleReportDTO(x));
	}

	public List<SaleSumaryDTO> getSumary(String minDate, String maxDate) {
	
		LocalDate endDate = getMaxDate(maxDate);
		LocalDate initDate = getMinDate(minDate, endDate);
		
		return repository.searchSumary(initDate, endDate);
	}
	
	private LocalDate getMaxDate(String maxDate) {
		
		LocalDate endDate;
		
		if(maxDate.isEmpty()) {
			endDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		else {
			
			String date = maxDate;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			endDate = LocalDate.parse(date,formatter);
			
		}
		
		return endDate;
	}
	
	private LocalDate getMinDate(String minDate, LocalDate maxDate) {
		
		LocalDate initDate;
		
		if(minDate.isEmpty()) {
			initDate = maxDate.minusYears(1L);
		}
		else {
			String date = minDate;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			initDate = LocalDate.parse(date,formatter);
		}
		
		return initDate;
		
	}
	
}

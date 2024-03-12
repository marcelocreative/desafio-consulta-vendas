package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dsmeta.dto.SaleSumaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleReportProjection;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

	
	@Query(nativeQuery = true, value="SELECT tb_sales.id, tb_sales.date, tb_sales.amount, tb_seller.name "
			+ "FROM tb_sales "
			+ "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id "
			+ "WHERE tb_sales.date BETWEEN :initDate AND :endDate AND UPPER(tb_seller.name) LIKE UPPER(CONCAT('%',:name,'%'))")
	Page<SaleReportProjection> searchReport(LocalDate initDate, LocalDate endDate, String name, Pageable pageable);

	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleSumaryDTO(obj.seller.name, SUM(obj.amount)) "
			+ "FROM Sale obj "
			+ "WHERE obj.date BETWEEN :initDate AND :endDate "
			+ "GROUP BY obj.seller.name")
	List<SaleSumaryDTO> searchSumary(LocalDate initDate, LocalDate endDate);

}

package com.infinite.solution.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infinite.solution.phoneshop.entity.SaleDetail;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long>{
	List<SaleDetail> findBySaleId(Long saleId);
}

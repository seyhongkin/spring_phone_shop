package com.infinite.solution.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.infinite.solution.phoneshop.entity.SaleDetail;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long>, JpaSpecificationExecutor<SaleDetail>{
	List<SaleDetail> findBySaleId(Long saleId);
}

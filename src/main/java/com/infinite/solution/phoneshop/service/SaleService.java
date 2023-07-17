package com.infinite.solution.phoneshop.service;

import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.dto.SaleDTO;
import com.infinite.solution.phoneshop.entity.Sale;

@Service
public interface SaleService {
	void create(SaleDTO saleDTO);
	Boolean cancelSale(Long saleId);
	Sale getById(Long saleId);
}

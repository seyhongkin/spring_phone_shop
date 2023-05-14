package com.infinite.solution.phoneshop.service;

import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.dto.SaleDTO;

@Service
public interface SaleService {
	void create(SaleDTO saleDTO);
}

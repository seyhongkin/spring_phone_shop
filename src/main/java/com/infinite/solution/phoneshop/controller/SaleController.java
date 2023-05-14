package com.infinite.solution.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinite.solution.phoneshop.dto.SaleDTO;
import com.infinite.solution.phoneshop.service.SaleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sale")
public class SaleController {
	private final SaleService saleService;
	
	@PostMapping
	public ResponseEntity<?> sale(@RequestBody SaleDTO saleDTO){
		saleService.create(saleDTO);
		return ResponseEntity.ok().build();
	}
}

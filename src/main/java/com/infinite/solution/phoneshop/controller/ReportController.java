package com.infinite.solution.phoneshop.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinite.solution.phoneshop.service.ReportService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reports")
public class ReportController {
	private final ReportService reportService;
	
	@GetMapping("/{startDate}/{endDate}")
	public ResponseEntity<?> productSold(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate){
		return ResponseEntity.ok(reportService.getProductSold(startDate, endDate));
	}
}

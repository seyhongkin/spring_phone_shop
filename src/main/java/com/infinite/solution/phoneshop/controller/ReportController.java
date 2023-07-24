package com.infinite.solution.phoneshop.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinite.solution.phoneshop.dto.ExpenseReportDTO;
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
	
	@GetMapping("v2/{startDate}/{endDate}")
	public ResponseEntity<?> productReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate){
		return ResponseEntity.ok(reportService.getProductSoldReport(startDate, endDate));
	}
	
	@GetMapping("expense/{startDate}/{endDate}")
	public ResponseEntity<?> expenseReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate){
			List<ExpenseReportDTO> expenseReportDTOs = reportService.getExpenseReport(startDate, endDate);
		return ResponseEntity.ok(expenseReportDTOs);
	}
}

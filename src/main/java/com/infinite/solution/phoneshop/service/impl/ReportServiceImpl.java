package com.infinite.solution.phoneshop.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.projection.ProductSold;
import com.infinite.solution.phoneshop.repository.ReportRepository;
import com.infinite.solution.phoneshop.service.ReportService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService{
	private final ReportRepository reportRepository;

	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		return reportRepository.findProductSold(startDate, endDate);
	}
	
}

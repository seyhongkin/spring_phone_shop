package com.infinite.solution.phoneshop.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.dto.ProductReportDTO;
import com.infinite.solution.phoneshop.projection.ProductSold;

@Service
public interface ReportService {
	List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
	List<ProductReportDTO> getProductSoldReport(LocalDate startDate, LocalDate endDate);
}

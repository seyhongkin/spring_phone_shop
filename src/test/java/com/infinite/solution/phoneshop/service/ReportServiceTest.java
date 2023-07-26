package com.infinite.solution.phoneshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.infinite.solution.phoneshop.dto.ExpenseReportDTO;
import com.infinite.solution.phoneshop.entity.Product;
import com.infinite.solution.phoneshop.entity.ProductImportHistory;
import com.infinite.solution.phoneshop.repository.ProductImportRepository;
import com.infinite.solution.phoneshop.repository.ProductRepository;
import com.infinite.solution.phoneshop.repository.SaleDetailRepository;
import com.infinite.solution.phoneshop.repository.SaleRepository;
import com.infinite.solution.phoneshop.service.impl.ReportServiceImpl;
import com.infinite.solution.phoneshop.spec.ProductImportHistorySpec;
import com.infinite.solution.phoneshop.utils.ReportServiceTestHelper;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
	private ReportService reportService;

	@Mock
	private SaleRepository saleRepository;
	@Mock
	private SaleDetailRepository detailRepository;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private ProductImportRepository productImportRepository;

	@BeforeEach
	public void setup() {
		reportService = new ReportServiceImpl(saleRepository, detailRepository, productRepository,
				productImportRepository);
	}

	@Test
	public void testGetProductSoldReport() {
		// given
		List<ProductImportHistory> importHistories = ReportServiceTestHelper.importHistories();
		List<Product> products = ReportServiceTestHelper.getProducts();

		// when
		when(productImportRepository.findAll(Mockito.any(ProductImportHistorySpec.class))).thenReturn(importHistories);
		when(productRepository.findAllById(Mockito.anySet())).thenReturn(products);

		List<ExpenseReportDTO> expenseReport = reportService.getExpenseReport(LocalDate.now().minusMonths(1), LocalDate.now());
		
		// then
		assertEquals(2, expenseReport.size());
		assertEquals(1, expenseReport.get(0).getProductId());
		assertEquals("iphone 14 pro max", expenseReport.get(0).getProductName());
		assertEquals(12, expenseReport.get(0).getUnit());
		assertEquals(BigDecimal.valueOf(14400d), expenseReport.get(0).getTotalAmount());
	}

}

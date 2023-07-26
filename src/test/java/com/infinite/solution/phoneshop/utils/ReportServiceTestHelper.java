package com.infinite.solution.phoneshop.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.infinite.solution.phoneshop.entity.Product;
import com.infinite.solution.phoneshop.entity.ProductImportHistory;

public class ReportServiceTestHelper {
	private static Product product1 = Product.builder().id(1L).name("iphone 14 pro max").build();

	private static Product product2 = Product.builder().id(2L).name("iphone 12 pro max").build();

	private static Product product3 = Product.builder().id(3L).name("samsung s24").build();

	public static List<Product> getProducts() {
		return List.of(product1, product2, product3);
	}

	public static List<ProductImportHistory> importHistories() {
		ProductImportHistory importHistory1 = ProductImportHistory.builder().product(product1).importUnit(12)
				.importDate(LocalDateTime.of(2023, 07, 11, 11, 21)).pricePerUnit(BigDecimal.valueOf(1200)).build();

		ProductImportHistory importHistory2 = ProductImportHistory.builder().product(product2).importUnit(32)
				.importDate(LocalDateTime.of(2023, 07, 21, 13, 14)).pricePerUnit(BigDecimal.valueOf(1400)).build();

		ProductImportHistory importHistory3 = ProductImportHistory.builder().product(product2).importUnit(32)
				.importDate(LocalDateTime.of(2023, 07, 29, 14, 19)).pricePerUnit(BigDecimal.valueOf(1400)).build();

		List<ProductImportHistory> histories = List.of(importHistory1, importHistory2, importHistory3);
		return histories;
	}

}

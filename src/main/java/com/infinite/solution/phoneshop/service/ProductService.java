package com.infinite.solution.phoneshop.service;

import com.infinite.solution.phoneshop.dto.ProductImportDTO;
import com.infinite.solution.phoneshop.entity.Product;

public interface ProductService {
	Product create(Product product);
	Product getById(Long id);
	void importProduct(ProductImportDTO importHistoryDto);
}

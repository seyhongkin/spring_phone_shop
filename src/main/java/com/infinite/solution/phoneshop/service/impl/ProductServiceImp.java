package com.infinite.solution.phoneshop.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.infinite.solution.phoneshop.dto.ProductImportDTO;
import com.infinite.solution.phoneshop.entity.Product;
import com.infinite.solution.phoneshop.entity.ProductImportHistory;
import com.infinite.solution.phoneshop.exceptions.ApiServiceException;
import com.infinite.solution.phoneshop.exceptions.ResourceNotFoundException;
import com.infinite.solution.phoneshop.mapper.ProductMapper;
import com.infinite.solution.phoneshop.repository.ProductImportRepository;
import com.infinite.solution.phoneshop.repository.ProductRepository;
import com.infinite.solution.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImp implements ProductService {
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	private final ProductImportRepository productImportRepository;

	@Override
	public Product create(Product product) {
		String color = product.getColor().getName();
		String model = product.getModel().getName();
		product.setName("%s %s".formatted(model, color));
		return productRepository.save(product);
	}

	@Override
	public Product getById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", id));
	}

	@Override
	public void importProduct(ProductImportDTO importHistoryDto) {
		// update product stock unit
		Product product = getById(importHistoryDto.getProductId());
		Integer availableUnit = 0;
		if (product.getAvailableUnit() != null) {
			availableUnit = product.getAvailableUnit();
		}
		availableUnit = availableUnit + importHistoryDto.getImportUnit();
		product.setAvailableUnit(availableUnit);
		productRepository.save(product);

		// save import history
		ProductImportHistory importHistory = productMapper.toProductImportHistory(importHistoryDto, product);
		productImportRepository.save(importHistory);
	}

	@Override
	public void setSalePrice(Long productId, BigDecimal salePrice) {
		Product product = getById(productId);
		if (product.getAvailableUnit() == 0) {
			throw new ApiServiceException(HttpStatus.BAD_REQUEST, "Can not set sale price when it out of stock");
		}
		product.setSalePrice(salePrice);
		productRepository.save(product);
	}

	@Override
	public void uploads(MultipartFile file) {
		Map<Integer, String> message = new HashMap<>();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheet("products");
			Iterator<Row> rowIterator = sheet.iterator();

			rowIterator.next(); // @IMPROVE
			while (rowIterator.hasNext()) {
				try {
					Row row = rowIterator.next();
					Cell cellModelId = row.getCell(0);
					long modelId = (long) cellModelId.getNumericCellValue();

					Cell cellColorId = row.getCell(1);
					long colorId = (long) cellColorId.getNumericCellValue();

					Cell cellPricePerUnit = row.getCell(2);
					BigDecimal pricePerUnit = BigDecimal.valueOf(cellPricePerUnit.getNumericCellValue());

					Cell cellUnit = row.getCell(3);
					long unit = (long) cellUnit.getNumericCellValue();

					Product product = getByModelIdAndColorId(modelId, colorId);
					ProductImportDTO productImportDTO = productMapper.toProductImportDTO(product, unit, pricePerUnit);

					importProduct(productImportDTO);
				} catch (ApiServiceException e) {
					message.put(1, e.getMessage());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Product getByModelIdAndColorId(Long modelId, Long colorId) {
		String message = "product with model_id = %d and color_id = %d was not found";
		return productRepository.findByModelIdAndColorId(modelId, colorId)
				.orElseThrow(() -> new ApiServiceException(HttpStatus.BAD_REQUEST, message));
	}
}

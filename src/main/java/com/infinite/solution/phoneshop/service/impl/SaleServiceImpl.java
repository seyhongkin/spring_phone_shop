package com.infinite.solution.phoneshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.dto.ProductSaleDTO;
import com.infinite.solution.phoneshop.dto.SaleDTO;
import com.infinite.solution.phoneshop.entity.Product;
import com.infinite.solution.phoneshop.entity.Sale;
import com.infinite.solution.phoneshop.entity.SaleDetail;
import com.infinite.solution.phoneshop.exceptions.ApiServiceException;
import com.infinite.solution.phoneshop.repository.ProductRepository;
import com.infinite.solution.phoneshop.repository.SaleDetailRepository;
import com.infinite.solution.phoneshop.repository.SaleRepository;
import com.infinite.solution.phoneshop.service.ProductService;
import com.infinite.solution.phoneshop.service.SaleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
	private final ProductService productService;
	private final ProductRepository productRepository;
	private final SaleRepository saleRepository;
	private final SaleDetailRepository saleDetailRepository;

	@Override
	public void create(SaleDTO saleDTO) {
		// validate product and stock
		List<Product> validProducts = validate(saleDTO);

		// pull stock
		pullStock(saleDTO, validProducts);

		// save sale and sale details
		save(saleDTO, validProducts);
	}

	private void save(SaleDTO saleDTO, List<Product> products) {
		// save sale
		Sale sale = new Sale();
		saleRepository.save(sale);

		Map<Long, Integer> saleUnits = saleDTO.getProducts().stream()
				.collect(Collectors.toMap(ProductSaleDTO::getProductId, ProductSaleDTO::getSaleUnit));
		
		// save sale details
		products.stream().forEach(p -> {
			SaleDetail detail = new SaleDetail();
			detail.setProduct(p);
			detail.setAmount(p.getSalePrice());
			detail.setSale(sale);
			detail.setUnit(saleUnits.get(p.getId()));
			saleDetailRepository.save(detail);
		});
	}

	private List<Product> validate(SaleDTO saleDTO) {
		List<Product> products = new ArrayList<>();
		saleDTO.getProducts().stream().forEach(ps -> {
			Product product = productService.getById(ps.getProductId());
			if (product.getAvailableUnit() < ps.getSaleUnit()) {
				throw new ApiServiceException(HttpStatus.BAD_REQUEST,
						"'%s' has only %d left".formatted(product.getName(), product.getAvailableUnit()));
			}
			if(product.getSalePrice() == null) {
				throw new ApiServiceException(HttpStatus.BAD_REQUEST,
						"'%s' has not set sale price yet".formatted(product.getName()));
			}
			products.add(product);
		});
		return products;
	}

	private void pullStock(SaleDTO saleDTO, List<Product> products) {

		Map<Long, Integer> saleUnits = saleDTO.getProducts().stream()
				.collect(Collectors.toMap(ProductSaleDTO::getProductId, ProductSaleDTO::getSaleUnit));

		products.stream().forEach(p -> {
			Integer availableUnit = p.getAvailableUnit() - saleUnits.get(p.getId());
			p.setAvailableUnit(availableUnit);
			productRepository.save(p);
		});
	}

}

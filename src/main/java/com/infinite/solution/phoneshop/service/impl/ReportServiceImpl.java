package com.infinite.solution.phoneshop.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.dto.ExpenseReportDTO;
import com.infinite.solution.phoneshop.dto.ProductReportDTO;
import com.infinite.solution.phoneshop.entity.Product;
import com.infinite.solution.phoneshop.entity.ProductImportHistory;
import com.infinite.solution.phoneshop.entity.SaleDetail;
import com.infinite.solution.phoneshop.projection.ProductSold;
import com.infinite.solution.phoneshop.repository.ProductImportRepository;
import com.infinite.solution.phoneshop.repository.ProductRepository;
import com.infinite.solution.phoneshop.repository.SaleDetailRepository;
import com.infinite.solution.phoneshop.repository.SaleRepository;
import com.infinite.solution.phoneshop.service.ReportService;
import com.infinite.solution.phoneshop.spec.ProductImportHistoryFilter;
import com.infinite.solution.phoneshop.spec.ProductImportHistorySpec;
import com.infinite.solution.phoneshop.spec.SaleDetailFIlter;
import com.infinite.solution.phoneshop.spec.SaleDetailSpec;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
	private final SaleRepository saleRepository;
	private final SaleDetailRepository detailRepository;
	private final ProductRepository productRepository;
	private final ProductImportRepository productImportRepository;

	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		return saleRepository.findProductSold(startDate, endDate);
	}

	@Override
	public List<ProductReportDTO> getProductSoldReport(LocalDate startDate, LocalDate endDate) {
		List<ProductReportDTO> list = new ArrayList<>();

		SaleDetailFIlter detailFilter = new SaleDetailFIlter();
		detailFilter.setStartDate(startDate);
		detailFilter.setEndDate(endDate);
		Specification<SaleDetail> spec = new SaleDetailSpec(detailFilter);
		List<SaleDetail> saleDetails = detailRepository.findAll(spec);

		List<Long> productIds = saleDetails.stream().map(sd -> sd.getProduct().getId()).toList();
		Map<Long, Product> productMap = productRepository.findAllById(productIds).stream()
				.collect(Collectors.toMap(Product::getId, Function.identity()));

		Map<Product, List<SaleDetail>> saleDetailMap = saleDetails.stream()
				.collect(Collectors.groupingBy(SaleDetail::getProduct));

		for (var entry : saleDetailMap.entrySet()) {
			Product product = productMap.get(entry.getKey().getId());
			List<SaleDetail> sdList = entry.getValue();

			// total unit
			Integer unit = sdList.stream().map(SaleDetail::getUnit).reduce(0, (a, b) -> a + b);
			/*
			 * Integer integer = sdList.stream().map(SaleDetail::getUnit) .reduce((a,b) ->
			 * a+b) .get();
			 */
			/*
			 * Double totalAmount = sdList.stream() .map(sd -> sd.getUnit() *
			 * sd.getAmount().doubleValue()) .reduce(0d, (a,b) -> a+b);
			 */
			double totalAmount = sdList.stream().mapToDouble(sd -> sd.getUnit() * sd.getAmount().doubleValue()).sum();

			// .map(sd -> sd.getUnit() * sd.getAmount().doubleValue())

			ProductReportDTO reportDTO = new ProductReportDTO();
			reportDTO.setProductId(product.getId());
			reportDTO.setProductName(product.getName());
			reportDTO.setUnit(unit);
			reportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
			list.add(reportDTO);
		}

		return list;
	}

	@Override
	public List<ExpenseReportDTO> getExpenseReport(LocalDate startDate, LocalDate endDate) {
		ProductImportHistoryFilter importHistoryFilter = new ProductImportHistoryFilter();
		importHistoryFilter.setStartDate(startDate);
		importHistoryFilter.setEndDate(endDate);
		
		//product_id, product_name, unit, total_amount
		
		ProductImportHistorySpec spec = new ProductImportHistorySpec(importHistoryFilter);
		List<ProductImportHistory> productImpHistories = productImportRepository.findAll(spec);
		
		Map<Product, List<ProductImportHistory>> importMap = productImpHistories.stream()
			.collect(Collectors.groupingBy(piHist -> piHist.getProduct()));
		
		Set<Long> productIds = productImpHistories.stream()
			.map(piHist -> piHist.getProduct().getId())
			.collect(Collectors.toSet());
			
		Map<Long, Product> productMap = productRepository.findAllById(productIds).stream()
			.collect(Collectors.toMap(p -> p.getId(), p -> p));
		
		var expenseReportDTOs = new ArrayList<ExpenseReportDTO>();
		for(var entry : importMap.entrySet()) {
			ExpenseReportDTO expenseReportDTO = new ExpenseReportDTO();
			expenseReportDTO.setProductId(entry.getKey().getId());
			//Name null or not?
			expenseReportDTO.setProductName(productMap.get(entry.getKey().getId()).getName());
			
			int totalUnit = entry.getValue().stream()
				.mapToInt(piHist -> piHist.getImportUnit())
				.sum();
			
			double totalAmount = entry.getValue().stream()
				.mapToDouble(piHist -> piHist.getImportUnit() * piHist.getPricePerUnit().doubleValue())
				.sum();
			
			expenseReportDTO.setUnit(totalUnit);
			expenseReportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
			
			expenseReportDTOs.add(expenseReportDTO);
		}
		
		expenseReportDTOs.sort((a,b)-> (int)(a.getProductId() - b.getProductId()));
		
		return expenseReportDTOs;
	}
}

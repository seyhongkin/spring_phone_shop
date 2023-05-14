package com.infinite.solution.phoneshop.dto;

import java.util.List;

import lombok.Data;

@Data
public class SaleDTO {
	private List<ProductSaleDTO> products;
	
	//might be include discount
}

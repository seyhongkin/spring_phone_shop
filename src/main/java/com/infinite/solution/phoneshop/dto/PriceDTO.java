package com.infinite.solution.phoneshop.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;

import lombok.Data;

@Data
public class PriceDTO {
	@DecimalMin(value = "0.01", message = "price have to be greater than 0.01")
	private BigDecimal salePrice;
}

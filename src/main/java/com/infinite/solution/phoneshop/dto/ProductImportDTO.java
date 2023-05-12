package com.infinite.solution.phoneshop.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductImportDTO {
	@NotNull(message = "productId can't be null")
	private Long productId;
	
	@Min(value = 1,message = "importUnit have to be greater than 1")
	private Integer importUnit;
	
	@DecimalMin(value = "0.001",message = "pricePerUnit have to be greate than 0.001")
	private BigDecimal pricePerUnit;
}

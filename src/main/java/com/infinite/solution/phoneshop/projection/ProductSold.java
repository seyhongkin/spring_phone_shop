package com.infinite.solution.phoneshop.projection;

import java.math.BigDecimal;

public interface ProductSold {
	Long getProductId();
	String getProductName();
	Integer getUnit();
	BigDecimal getTotalAmount();
}

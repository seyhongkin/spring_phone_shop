package com.infinite.solution.phoneshop.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BrandDTO {
	//private Integer id;
	@NotNull(message = "Brand name is required")
	@NotEmpty(message = "Brand name can't be empty")
	private String name;
}

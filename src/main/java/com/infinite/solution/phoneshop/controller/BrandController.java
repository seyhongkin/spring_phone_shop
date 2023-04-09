package com.infinite.solution.phoneshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infinite.solution.phoneshop.dto.BrandDTO;
import com.infinite.solution.phoneshop.entity.Brand;
import com.infinite.solution.phoneshop.service.BrandService;
import com.infinite.solution.phoneshop.service.util.Mapper;

@RestController
@RequestMapping("brands")
public class BrandController {
	@Autowired
	private BrandService brandservice;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
		Brand brand = Mapper.toBrand(brandDTO);
		brand = brandservice.create(brand);
		return ResponseEntity.ok(Mapper.toBrandDTO(brand));
	}
}

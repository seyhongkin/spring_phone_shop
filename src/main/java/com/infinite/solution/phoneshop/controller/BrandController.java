package com.infinite.solution.phoneshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<?> create(@Valid @RequestBody BrandDTO brandDTO){
		Brand brand = Mapper.toBrand(brandDTO);
		brand = brandservice.create(brand);
		return ResponseEntity.ok(Mapper.toBrandDTO(brand));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer id){
		Brand brand = brandservice.getById(id);
		return ResponseEntity.ok(Mapper.toBrandDTO(brand));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody BrandDTO brandDto){
		Brand newBrand = Mapper.toBrand(brandDto);
		Brand updatedBrand = brandservice.update(id, newBrand);
		return ResponseEntity.ok(Mapper.toBrandDTO(updatedBrand));
	}
}

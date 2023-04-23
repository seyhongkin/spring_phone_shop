package com.infinite.solution.phoneshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.entity.Brand;
import com.infinite.solution.phoneshop.exceptions.ApiServiceException;
import com.infinite.solution.phoneshop.exceptions.ResourceNotFoundException;
import com.infinite.solution.phoneshop.repository.BrandRepository;
import com.infinite.solution.phoneshop.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Brand create(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public Brand getById(Integer id) {
//		Optional<Brand> optionalBrand = brandRepository.findById(id);
//		if (optionalBrand.isPresent()) {
//			return optionalBrand.get();
//		}
//		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Brand id = " + id + " are not found");

		
		//best practice
		return brandRepository.findById(id)
				//.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "brand_id = " + id + " are not found"));
				.orElseThrow(() -> new ResourceNotFoundException("Brand", id));
	}

	@Override
	public Brand update(Integer Id, Brand brandUpdate) {
		Brand brand = getById(Id);
		brand.setName(brandUpdate.getName()); //@TODO improve
		return brandRepository.save(brand);
	}

	@Override
	public List<Brand> getBrands() {
		return brandRepository.findAll();
	}

	@Override
	public List<Brand> getBrands(String name) {
		return brandRepository.findByNameContainingIgnoreCase(name);
	}

}

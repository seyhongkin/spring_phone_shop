package com.infinite.solution.phoneshop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.entity.Brand;
import com.infinite.solution.phoneshop.exceptions.ApiServiceException;
import com.infinite.solution.phoneshop.exceptions.ResourceNotFoundException;
import com.infinite.solution.phoneshop.repository.BrandRepository;
import com.infinite.solution.phoneshop.service.BrandService;
import com.infinite.solution.phoneshop.service.util.PageUtil;
import com.infinite.solution.phoneshop.spec.BrandFilter;
import com.infinite.solution.phoneshop.spec.BrandSpec;

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

	/* NO NEED TO USE THIS
	@Override
	public List<Brand> getBrands() {
		return brandRepository.findAll();
	}
	*/

	@Override
	public List<Brand> getBrands(String name) {
		return brandRepository.findByNameContainingIgnoreCase(name);
	}

	/*
	@Override
	public List<Brand> getBrands(Map<String, String> param) {
		BrandFilter brandFilter = new BrandFilter();
		if(param.containsKey("name")) {
			String name = param.get("name");
			brandFilter.setName(name);
		}
		
		if(param.containsKey("id")) {
			Integer id = Integer.parseInt(param.get("id"));
			brandFilter.setId(id);
		}
		
		BrandSpec brandSpec = new BrandSpec(brandFilter);
		
		return brandRepository.findAll(brandSpec);
	}
	*/

	@Override
	public Page<Brand> getBrands(Map<String, String> param) {
		BrandFilter brandFilter = new BrandFilter();
		if(param.containsKey("name")) {
			String name = param.get("name");
			brandFilter.setName(name);
		}
		
		if(param.containsKey("id")) {
			Integer id = Integer.parseInt(param.get("id"));
			brandFilter.setId(id);
		}
		
		BrandSpec brandSpec = new BrandSpec(brandFilter);
		
		
		//@TODO make function for pageable
		int pageNum = 1;
		if(param.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNum = Integer.parseInt(param.get(PageUtil.PAGE_NUMBER));
		}
		
		int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
		if(param.containsKey(PageUtil.PAGE_LIMIT)) {
			pageLimit = Integer.parseInt(param.get(PageUtil.PAGE_LIMIT));
		}
		
		Pageable pageable = PageUtil.getPageable(pageNum, pageLimit);
		
		//return brandRepository.findAll(brandSpec);
		Page<Brand> page = brandRepository.findAll(brandSpec, pageable);
		return page;
	}
}

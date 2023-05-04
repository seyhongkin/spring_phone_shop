package com.infinite.solution.phoneshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infinite.solution.phoneshop.dto.BrandDTO;
import com.infinite.solution.phoneshop.dto.ModelDTO;
import com.infinite.solution.phoneshop.dto.PageDTO;
import com.infinite.solution.phoneshop.entity.Brand;
import com.infinite.solution.phoneshop.entity.Model;
import com.infinite.solution.phoneshop.mapper.BrandMapper;
import com.infinite.solution.phoneshop.mapper.ModelEntityMapper;
import com.infinite.solution.phoneshop.service.BrandService;
import com.infinite.solution.phoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("brands")
public class BrandController {
	private final BrandService brandservice;
	private final ModelService modelService;
	private final ModelEntityMapper modelMapper;

	// @RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandservice.create(brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer id) {
		Brand brand = brandservice.getById(id);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody BrandDTO brandDto) {
		Brand newBrand = BrandMapper.INSTANCE.toBrand(brandDto);
		Brand updatedBrand = brandservice.update(id, newBrand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(updatedBrand));
	}

	/*
	 * NO NEED TO USE THIS
	 * 
	 * @GetMapping public ResponseEntity<?> getBrands(){ List<BrandDTO> listBrands =
	 * brandservice.getBrands() .stream() .map(brand ->
	 * BrandMapper.INSTANCE.toBrandDTO(brand)) .collect(Collectors.toList()); return
	 * ResponseEntity.ok(listBrands); }
	 */

	/*
	 * OLD VERSION
	 * 
	 * @GetMapping public ResponseEntity<?> getBrands(@RequestParam("name") String
	 * name){ List<BrandDTO> listBrands = brandservice.getBrands(name) .stream()
	 * .map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
	 * .collect(Collectors.toList()); return ResponseEntity.ok(listBrands); }
	 */

	@GetMapping
	public ResponseEntity<?> getBrands(@RequestParam Map<String, String> param) {
		Page<Brand> page = brandservice.getBrands(param);

		PageDTO pageDTO = new PageDTO(page);
		/*
		 * List<BrandDTO> listBrands = brandservice.getBrands(param) .stream()
		 * .map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
		 * .collect(Collectors.toList());
		 */
		return ResponseEntity.ok(pageDTO);
	}

	@GetMapping("{id}/models")
	public ResponseEntity<?> getModelsByBrand(@PathVariable("id") Integer brandId) {
		List<Model> models = modelService.getModelsByBrandId(brandId);
		List<ModelDTO> modelDto = models.stream()
			.map(modelMapper::toModelDto)
			.toList();
		return ResponseEntity.ok(modelDto);
	}
}

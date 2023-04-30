package com.infinite.solution.phoneshop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.infinite.solution.phoneshop.entity.Brand;

@DataJpaTest
public class BrandRepositoryTest {
	@Autowired
	private BrandRepository brandRepository;
	
	@Test
	public void testFindByNameContainingIgnoreCase() {
		//given
		Brand brand = new Brand();
		brand.setName("Apple");
		brandRepository.save(brand);
		
		//when
		List<Brand> brands = brandRepository.findByNameContainingIgnoreCase("a");
		
		//then
		assertEquals(1, brands.size());
		assertEquals(1, brands.get(0).getId());
		assertEquals("Apple", brands.get(0).getName());
	}
}

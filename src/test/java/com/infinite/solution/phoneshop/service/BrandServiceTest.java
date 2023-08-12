package com.infinite.solution.phoneshop.service;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.infinite.solution.phoneshop.entity.Brand;
import com.infinite.solution.phoneshop.exceptions.ResourceNotFoundException;
import com.infinite.solution.phoneshop.repository.BrandRepository;
import com.infinite.solution.phoneshop.service.impl.BrandServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
	@Mock
	private BrandRepository brandRepository;

	private BrandService brandService;

	@BeforeEach
	private void setUpService() {
		brandService = new BrandServiceImpl(brandRepository);
	}
	
	@Captor
	private ArgumentCaptor<Brand> argumentCaptor;

	/*
	 * @Test public void testCreate() { //given Brand brand = new Brand();
	 * brand.setName("Samsung"); brand.setId(1);
	 * 
	 * //when brandRepository.save is invoke it will return brand object back
	 * when(brandRepository.save(any(Brand.class))).thenReturn(brand);
	 * 
	 * //brandService.create has brandRepository.save in it body; it invoke to get
	 * return value Brand brandReturn = brandService.create(new Brand());
	 * 
	 * //then assertEquals(1, brandReturn.getId()); assertEquals("Samsung",
	 * brandReturn.getName()); }
	 */

	@Test
	public void testCreate() {
		// given
		Brand brand = new Brand();
		brand.setName("Apple");

		// when
		brandService.create(brand);

		// then
		verify(brandRepository, times(1)).save(brand);
	}

	@Test
	public void testGetByIdSuccess() {
		// given
		Brand brand = new Brand();
		brand.setName("Samsung");
		brand.setId(1L);

		// when
		when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
		Brand brandReturn = brandService.getById(1L);

		// then
		assertEquals(1, brandReturn.getId());
		assertEquals("Samsung", brandReturn.getName());
	}

	@Test
	public void testGetByIdThrow() {
		// given

		// when
		when(brandRepository.findById(2L)).thenReturn(Optional.empty());

		//assertThrows(ResourceNotFoundException.class, ()->brandService.getById(2));
		assertThatThrownBy(() -> brandService.getById(2L))
				.isInstanceOf(ResourceNotFoundException.class)
				//.hasMessage(String.format("%s_id = %d is not found", "Brand", 2));
				.hasMessageEndingWith("not found");
		// then
	}
	
	@Test
	public void testUpdate() {
		//given
		Brand givenBrand = new Brand(1L, "Oppo");
		Brand updateTo = new Brand(1L, "OPPO");
		
		//when
		when(brandRepository.findById(1L)).thenReturn(Optional.ofNullable(givenBrand));
		brandService.update(1l, updateTo);
		
		//then
		verify(brandRepository, times(1)).findById(1L);
		verify(brandRepository, times(1)).save(argumentCaptor.capture());
		assertEquals("OPPO", argumentCaptor.getValue().getName());
		assertEquals(1l, argumentCaptor.getValue().getId());
	}
}

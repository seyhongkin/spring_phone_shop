package com.infinite.solution.phoneshop.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GeneralUtilsTest {
	
	@Test
	public void testToLength() {
		//given
		List<String> name = List.of("sok","sokha","chea");
		//when
		List<Integer> length = GeneralUtils.toLength(name);
		//then (expected)
		assertEquals(3, length.size());
		assertEquals(3, length.get(0));
		assertEquals(5, length.get(1));
		assertEquals(4, length.get(2));
		
	}
	
	@Test
	public void testGetEvenNumber() {
		//given
		List<Integer> num = List.of(1,2,12,4,21,53,23,44);
		//when
		List<Integer> evenNumber = GeneralUtils.getEvenNumber(num);
		//then
		assertEquals(4, evenNumber.size());
		assertEquals(2, evenNumber.get(0));
		assertEquals(12, evenNumber.get(1));
		assertEquals(4, evenNumber.get(2));
		assertEquals(44, evenNumber.get(3));
	}
}

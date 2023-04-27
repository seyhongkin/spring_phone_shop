package com.infinite.solution.phoneshop.utils;

import java.util.List;

public class GeneralUtils {
	public static List<Integer> toLength(List<String> list) {

		return list.stream()
				.map(s -> s.length())
				.toList();
	}

	public static List<Integer> getEvenNumber(List<Integer> num) {
		return num.stream()
				.filter(n -> n % 2 == 0)
				.toList();
	}
}

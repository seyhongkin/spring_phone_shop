package com.infinite.solution.phoneshop.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.infinite.solution.phoneshop.entity.ProductImportHistory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductImportHistorySpec implements Specification<ProductImportHistory> {
	private ProductImportHistoryFilter importHistoryFilter;

	@Override
	public Predicate toPredicate(Root<ProductImportHistory> piHistory, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		if (Objects.nonNull(importHistoryFilter.getStartDate())) {
			Predicate startDate = cb.greaterThanOrEqualTo(piHistory.get("importDate"), importHistoryFilter.getStartDate().atStartOfDay());
			predicates.add(startDate);
		}
		if (Objects.nonNull(importHistoryFilter.getEndDate())) {
			Predicate endDate = cb.lessThanOrEqualTo(piHistory.get("importDate"), importHistoryFilter.getEndDate().atTime(23,59));
			predicates.add(endDate);
		}
		Predicate predicate = cb.and(predicates.toArray(Predicate[]::new));
		return predicate;
	}

}

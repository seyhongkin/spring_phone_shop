package com.infinite.solution.phoneshop.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.infinite.solution.phoneshop.entity.Sale;
import com.infinite.solution.phoneshop.entity.SaleDetail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleDetailSpec implements Specification<SaleDetail> {
	private SaleDetailFIlter detailFilter;

	@Override
	public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		Join<SaleDetail, Sale> sale = saleDetail.join("sale");
		if (Objects.nonNull(detailFilter.getStartDate())) {
			Predicate greaterThanOrEqualTo = cb.greaterThanOrEqualTo(sale.get("saleDate"), detailFilter.getStartDate().atTime(0, 0));
			predicates.add(greaterThanOrEqualTo);
		}
		if (Objects.nonNull(detailFilter.getEndDate())) {
			Predicate lessThanOrEqualTo = cb.lessThanOrEqualTo(sale.get("saleDate"), detailFilter.getEndDate().atTime(23, 59));
			predicates.add(lessThanOrEqualTo);
		}
		Predicate predicate = cb.and(predicates.toArray(Predicate[]::new));
		return predicate;
	}

}

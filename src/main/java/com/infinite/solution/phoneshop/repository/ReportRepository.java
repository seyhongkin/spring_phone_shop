package com.infinite.solution.phoneshop.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infinite.solution.phoneshop.entity.Color;
import com.infinite.solution.phoneshop.projection.ProductSold;

public interface ReportRepository extends JpaRepository<Color, Long>{
	@Query(value = "select p.product_id as productId, p.product_name productName, sum(sd.unit) as unit, sum(sd.unit * sd.amount) totalAmount\r\n"
			+ "from sale_details as sd\r\n"
			+ "inner join sales as s on sd.sale_id = s.sale_id\r\n"
			+ "inner join products as p on sd.product_id = p.product_id\r\n"
			+ "where date(s.sale_date) between '2023-07-15' and '2023-07-16'\r\n"
			+ "group by p.product_id, p.product_name", nativeQuery = true)
	List<ProductSold> findProductSold(LocalDate startDate, LocalDate endDate);
}

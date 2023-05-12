package com.infinite.solution.phoneshop.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Data
@Entity
@Table(name = "productImportHistories")
public class ProductImportHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "import_id")
	private Long id;
	
	@Column(name = "import_date")
	@CreationTimestamp
	private LocalDateTime importDate;
	
	@Column(name = "import_unit")
	private Integer importUnit;
	
	@Column(name = "price_per_unit")
	private BigDecimal pricePerUnit;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}

package com.project;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import java.util.Date;

@Entity
@Table(name = "category", uniqueConstraints = { @UniqueConstraint(columnNames = { "category_id" }) })
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", nullable = false, unique = true, length = 11)
	private int category_id;

	@Column(name = "NAME")
	private String name;

	@OneToMany
	@JoinTable(name = "category_sales", joinColumns = { @JoinColumn(name = "category_id", referencedColumnName = "category_id") }, inverseJoinColumns = { @JoinColumn(name = "sale_id", referencedColumnName = "sale_id", unique = true) })
	private List<Sale> categorySales;

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Sale> getCategorySales() {
		return categorySales;
	}

	public void setCategorySales(List<Sale> categorySales) {
		this.categorySales = categorySales;
	}

	public void addCategorySale(Date startDate, Date endDate,
			double percentDiscount) {

	}
}

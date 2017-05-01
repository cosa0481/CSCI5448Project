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

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
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
	private List<Sale> categorySales = new ArrayList<Sale>();

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

	public void addCategorySale(Sale catSale) {
		Session s = DatabaseManager.getInstance().getSession();
		s.beginTransaction();
		s.save(catSale);
		s.refresh(this);
		
		if (!categorySales.contains(catSale)) {
			categorySales.add(catSale);
		}
		
		s.saveOrUpdate(this);
		
		s.getTransaction().commit();
		DatabaseManager.getInstance().closeSession();
	}
	
	public static List<Category> searchCategories(String keyword) {

		Session session = null;
		List<Category> categories = new ArrayList<>();

		try {
			session = DatabaseManager.getInstance().getSession();

			Criteria cri = session.createCriteria(Category.class);
			cri.add(Restrictions.ilike("name", "%" + keyword + "%"));
			List<Object> results = cri.list();

			for (Object o : results) {
				categories.add((Category) o);
			}
			return categories;
		} finally {
			session.close();
		}

	}
}

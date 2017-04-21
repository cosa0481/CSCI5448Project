package com.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Administrator", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class Administrator extends Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true, length=11)
	private int id;
	
	// Pointless comment to move file
	public void addSale(Sale sale, Category category) {
		
	}
	
	public void processReturn(Order order) {
		
	}
}

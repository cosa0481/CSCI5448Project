package com.project;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="administrator", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class Administrator extends Person {
	
	
	// Pointless comment to move file
	public void addSale(Sale sale, Category category) {
		
	}
	
	public void processReturn(Order order) {
		
	}
}

package com.project;

import javax.persistence.*;

@Entity
@Table(name="venmo_details")
public class VenmoDAO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="venmo_id", nullable=false, unique=true)
	private int id;
	
	@Column(name="venmo_user_name", nullable=false)
	private String venmoUserName;

	@OneToOne(optional = false)
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;
	
	public String getVenmoUserName() {
		return venmoUserName;
	}

	public void setVenmoUserName(String venmoUserName) {
		this.venmoUserName = venmoUserName;
	}
}

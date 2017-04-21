package com.project;

import javax.persistence.*;

@Entity
@Table(name="VenmoDAO")
public class VenmoDAO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true)
	private int id;
	
	@Column(name="VENMOUSERNAME")
	private String venmoUserName;

	public String getVenmoUserName() {
		return venmoUserName;
	}

	public void setVenmoUserName(String venmoUserName) {
		this.venmoUserName = venmoUserName;
	}
}

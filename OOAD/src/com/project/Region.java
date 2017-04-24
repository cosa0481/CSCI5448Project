package com.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="region", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"region_id"})})
public class Region {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="region_id", nullable=false, unique=true, length=11)
	private int id;
	
	@Column(name="TITLE", length=20, nullable=true)
	private String title;

	@Column(name="ZIPCODE", length=20, nullable=true)
	private int zipcode;
	
	@Column(name="STATE", length=20, nullable=true)
	private String state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}

package com.project;

import javax.persistence.*;

@MappedSuperclass
abstract public class Person {
	
	//TODO
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true)
	private int id;

	@Column(name="firstName", length=20, nullable=true)
	private String firstName;
	
	@Column(name="lastName", length=20, nullable=true)
	private String lastName;
	
	@Column(name="username", length=20, nullable=true)
	private String username;
	
	@Column(name="password", length=20, nullable=true)
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean login(String userName, String password) {
		//TODO
		return false;
	}
}

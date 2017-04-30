package com.project;

import java.util.List;

import javax.persistence.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@MappedSuperclass
abstract public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private int id;

	@Column(name = "firstName", length = 20, nullable = true)
	private String firstName;

	@Column(name = "lastName", length = 20, nullable = true)
	private String lastName;

	@Column(name = "username", length = 20, nullable = true,unique=true)
	private String username;

	@Column(name = "password", length = 20, nullable = true)
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

	public static Object login(String userName, String password, Class userType) {
		Session session = null;
		try {
			session = DatabaseManager.getInstance().getSession();

			Criteria cri = session.createCriteria(userType);
			cri.add(Restrictions.eq("username", userName));
			cri.add(Restrictions.eq("password", password));

			List<Object> userProfiles = cri.list();

			if (userProfiles.size() == 0) {
				return null;
			}

			return userProfiles.get(0);
		} finally {
			session.close();
		}

	}

}

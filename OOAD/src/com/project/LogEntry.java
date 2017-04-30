package com.project;

import java.util.Date;
import javax.persistence.*;

import org.hibernate.Session;

@Entity
@Table(name = "log",
	   uniqueConstraints={@UniqueConstraint(columnNames={"log_id"})})
public class LogEntry {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "log_id", nullable = false, unique = true)
	public int id;
	
	@Column(name = "log_type")
	public String log_type;
	
	@Column(name = "log_entry")
	public String log_entry;
	
	@Column(name = "date")
	public Date log_date;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLog_type() {
		return log_type;
	}

	public void setLog_type(String log_type) {
		this.log_type = log_type;
	}

	public String getLog_entry() {
		return log_entry;
	}

	public void setLog_entry(String log_entry) {
		this.log_entry = log_entry;
	}

	public Date getLog_date() {
		return log_date;
	}

	public void setLog_date(Date log_date) {
		this.log_date = log_date;
	}
	
	public void addLogEntry(String type, String entry, Date timeStamp) {
		Session s = DatabaseManager.getInstance().getSession();
		s.beginTransaction();
		
		this.setLog_type(type);
		this.setLog_entry(entry);
		this.setLog_date(timeStamp);
		
		s.save(this);
		
		s.getTransaction().commit();
		
		DatabaseManager.getInstance().closeSession();
 	}
}

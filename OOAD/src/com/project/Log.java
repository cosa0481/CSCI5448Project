package com.project;

import java.util.Date;

public class Log {
	private String logFile;
	
	public String getLogFile() { return logFile; }
	
	public void setLogFile(String name) { logFile = name; }
	
	public void addLogEntry(String entry, Date timeStamp) {
		// Open logFile
		// Write timeStamp to logFile line
		// Write entry to same logFile line
		// Save logFile
		// Close logFile
 	}
}

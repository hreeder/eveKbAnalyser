package com.pleaseignore.killboardAnalyser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Resources {
	private String dbHost;
	private String dbName;
	private String dbUser;
	private String dbPass;
	
	
	public Resources(String host, String name, String user, String pass) {
		dbHost = host;
		dbName = name;
		dbUser = user;
		dbPass = pass;
	}
	
	public String getCorpNameByID(int corpID) {
		Connection conn;
		Statement stmt;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+dbHost+"/"+dbName, dbUser, dbPass);
			stmt = conn.createStatement();
			
			String sql = "SELECT crp_name FROM kb3_corps WHERE crp_id = " + corpID;
			ResultSet results = stmt.executeQuery(sql);
			
			while (results.next()) {
				return results.getString("crp_name");
			}
		} catch (Exception e) {
			System.out.println("OH GOD SOMETHING BAD HAPPENED.");
			System.out.println("Error Location");
			System.out.println("Function: getCorpNameByID");
			System.out.println("Filename: Resources.java");
		}
		return null;
	}
	
	public int getCorpIdByName(String name) {
		Connection conn;
		Statement stmt;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+dbHost+"/"+dbName, dbUser, dbPass);
			stmt = conn.createStatement();
			
			String sql = "SELECT crp_id FROM kb3_corps WHERE crp_name = '" + name + "'";
			
			ResultSet results = stmt.executeQuery(sql);
			
			while (results.next()) {
				return results.getInt("crp_id");
			}
		} catch (Exception e) {
			System.out.println("ERROR. BAD STUFF HAPPENED");
			System.out.println(e.toString());			
		}
		
		return 0;
	}
}
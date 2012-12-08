package com.pleaseignore.killboardAnalyser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
			System.out.println("OH GOD SOMETHING BAD HAPPENED.");
			System.out.println("Error Location");
			System.out.println("Function: getCorpIdByName");
			System.out.println("Filename: Resources.java");
		}
		
		return 0;
	}
	
	public ArrayList<Integer> getInvolvedPeopleFromKillmail(int killmailID) {
		ArrayList<Integer> out = new ArrayList<Integer>();
		Connection conn;
		Statement stmt;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+dbHost+"/"+dbName, dbUser, dbPass);
			stmt = conn.createStatement();
			
			String sql = "SELECT ind_plt_id FROM kb3_inv_detail WHERE ind_kll_id = " + killmailID;
			
			ResultSet results = stmt.executeQuery(sql);
			
			while (results.next()) {
				out.add(results.getInt("ind_plt_id"));
			}
		} catch (Exception e) {
			System.out.println("OH GOD SOMETHING BAD HAPPENED.");
			System.out.println("Error Location");
			System.out.println("Function: getInvolvedPeopleFromKillmail");
			System.out.println("Filename: Resources.java");
		}
		
		return out;
	}
	
	public ArrayList<Integer> getKillmailsBetweenDates(String dateTimeStart, String dateTimeEnd) {
		ArrayList<Integer> out = new ArrayList<Integer>();
		
		return out;
	}
	
	
}

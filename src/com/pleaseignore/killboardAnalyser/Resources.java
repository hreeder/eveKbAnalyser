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
	private Connection conn;
	private Statement stmt;
	
	
	public Resources(String host, String name, String user, String pass) {
		dbHost = host;
		dbName = name;
		dbUser = user;
		dbPass = pass;
	}
	
	public String getCorpByID(int corpID) {
		String[] output = new String[1];
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+dbHost+"/"+dbName, dbUser, dbPass);
			stmt = conn.createStatement();
			
			String sql = "SELECT crp_name, all_id FROM kb3_corps WHERE crp_id = " + corpID;
			ResultSet results = stmt.executeQuery(sql);
			
			while (results.next()) {
				output[0] = results.getString("crp_name");
				output[1] = results.getString("all_id");
			}
		} catch (Exception e) {
			System.out.println("OH GOD SOMETHING BAD HAPPENED.");
			System.out.println("Error Location");
			System.out.println("Function: getCorpByID");
			System.out.println("Filename: Resources.java");
		}
		return null;
	}
	
	public String getAllianceByID(int allID) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+dbHost+"/"+dbName, dbUser, dbPass);
			stmt = conn.createStatement();
			
			String sql = "SELECT all_name FROM kb3_alliances WHERE all_id = " + allID;
			ResultSet results = stmt.executeQuery(sql);
			
			while (results.next()) {
				return results.getString("all_name");
			}
		} catch (Exception e) {
			System.out.println("OH GOD SOMETHING BAD HAPPENED.");
			System.out.println("Error Location");
			System.out.println("Function: getAllianceByID");
			System.out.println("Filename: Resources.java");
		}
		return null;
	}
	
	public ArrayList<Integer> getInvolvedPeopleFromKillmail(int killmailID) {
		ArrayList<Integer> out = new ArrayList<Integer>();

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

		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+dbHost+"/"+dbName, dbUser, dbPass);
			stmt = conn.createStatement();
			
			String sql = "SELECT kll_id FROM kb3_kills WHERE kll_timestamp >= '" + dateTimeStart + "' AND kll_timestamp < '" + dateTimeEnd +"'";
			
			ResultSet results = stmt.executeQuery(sql);
			
			while (results.next()) {
				out.add(results.getInt("kll_id"));
			}
		} catch (Exception e) {
			System.out.println("OH GOD SOMETHING BAD HAPPENED.");
			System.out.println("Error Location");
			System.out.println("Function: getKillmailsBetweenDates");
			System.out.println("Filename: Resources.java");
		}
		
		return out;
	}
	
	
}

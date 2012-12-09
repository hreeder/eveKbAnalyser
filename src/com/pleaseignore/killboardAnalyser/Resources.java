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
	
	public ResultSet getCorpByID(int corpID) {
		ResultSet output = null;
		try {
			Connection conn;
			Statement stmt;
			conn = DriverManager.getConnection("jdbc:mysql://"+dbHost+"/"+dbName, dbUser, dbPass);
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM kb3_corps WHERE crp_id = " + corpID;
			output = stmt.executeQuery(sql);
			
			conn.close();
		} catch (Exception e) {
			System.out.println("OH GOD SOMETHING BAD HAPPENED.");
			System.out.println("Error Location");
			System.out.println("Function: getCorpByID");
			System.out.println("Filename: Resources.java");
			System.err.println(e.toString());
		}
		
		
		return output;
	}
	
	public ResultSet getAllianceByID(int allID) {
		ResultSet output = null;
		try {
			Connection conn;
			Statement stmt;
			conn = DriverManager.getConnection("jdbc:mysql://"+dbHost+"/"+dbName, dbUser, dbPass);
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM kb3_alliances WHERE all_id = " + allID;
			output = stmt.executeQuery(sql);
			
			conn.close();
		} catch (Exception e) {
			System.out.println("OH GOD SOMETHING BAD HAPPENED.");
			System.out.println("Error Location");
			System.out.println("Function: getAllianceByID");
			System.out.println("Filename: Resources.java");
			System.err.println(e.toString());
		}
		return output;
	}
	
	public ResultSet getPlayerByID(int playerID) {
		ResultSet output = null;
		try {
			Connection conn;
			Statement stmt;
			conn = DriverManager.getConnection("jdbc:mysql://"+dbHost+"/"+dbName, dbUser, dbPass);
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM kb3_pilots WHERE plt_id = " + playerID;
			ResultSet results = stmt.executeQuery(sql);
			
			conn.close();
		} catch (Exception e) {
			System.out.println("OH GOD SOMETHING BAD HAPPENED.");
			System.out.println("Error Location");
			System.out.println("Function: getPlayerByID");
			System.out.println("Filename: Resources.java");
			System.err.println(e.toString());
		}
		return output;
	}
	
	public ArrayList<Player> getInvolvedPeopleFromKillmail(int killmailID) {
		ArrayList<Player> out = new ArrayList<Player>();

		try {
			Connection conn;
			Statement stmt;
			conn = DriverManager.getConnection("jdbc:mysql://"+dbHost+"/"+dbName, dbUser, dbPass);
			stmt = conn.createStatement();
			
			String sql = "SELECT ind_plt_id FROM kb3_inv_detail WHERE ind_kll_id = " + killmailID;
			
			ResultSet results = stmt.executeQuery(sql);
			
			while (results.next()) {
				out.add(new Player(getPlayerByID(results.getInt("ind_plt_id")), this));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println("OH GOD SOMETHING BAD HAPPENED.");
			System.out.println("Error Location");
			System.out.println("Function: getInvolvedPeopleFromKillmail");
			System.out.println("Filename: Resources.java");
			System.err.println(e.toString());
		}
		
		return out;
	}
	
	public ArrayList<Kill> getKillmailsBetweenDates(String dateTimeStart, String dateTimeEnd) {
		ArrayList<Kill> out = new ArrayList<Kill>();

		try {
			Connection conn;
			Statement stmt;
			conn = DriverManager.getConnection("jdbc:mysql://"+dbHost+"/"+dbName, dbUser, dbPass);
			stmt = conn.createStatement();
			
			String sql = "SELECT kll_id, kll_victim_id, kll_ship_id FROM kb3_kills WHERE kll_timestamp >= '" + dateTimeStart + "' AND kll_timestamp < '" + dateTimeEnd +"'";
			
			ResultSet results = stmt.executeQuery(sql);
			
			while (results.next()) {
				out.add(new Kill(
						results.getInt("kll_id"),
						results.getInt("kll_victim_id"),
						results.getInt("kll_ship_id"),
						this));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println("OH GOD SOMETHING BAD HAPPENED.");
			System.out.println("Error Location");
			System.out.println("Function: getKillmailsBetweenDates");
			System.out.println("Filename: Resources.java");
			System.err.println(e.toString());
		}
		
		return out;
	}
	
	public String getShipClassByShipID(int shipID){
		String output = null;
		try {
			Connection conn;
			Statement stmt;
			conn = DriverManager.getConnection("jdbc:mysql://"+dbHost+"/"+dbName, dbUser, dbPass);
			stmt = conn.createStatement();
			
			String sql = "SELECT shp_class FROM kb3_ships WHERE shp_id = " + shipID;
			ResultSet results = stmt.executeQuery(sql);
			
			while (results.next()) {
				sql = "SELECT scl_class FROM kb3_ship_classes WHERE scl_id = " + results.getInt("shp_class");
			}
			
			results = stmt.executeQuery(sql);
			
			while (results.next()) {
				output = results.getString("scl_class");
			}
			
			conn.close();
		} catch (Exception e) {
			System.out.println("OH GOD SOMETHING BAD HAPPENED.");
			System.out.println("Error Location");
			System.out.println("Function: getShipClassByShipID");
			System.out.println("Filename: Resources.java");
			System.err.println(e.toString());
		}
		return output;
	}
}

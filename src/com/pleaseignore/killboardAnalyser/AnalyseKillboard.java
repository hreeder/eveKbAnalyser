package com.pleaseignore.killboardAnalyser;

public class AnalyseKillboard {

	private static String dbHost = "gandalf.pkservers.co.uk";
	private static String dbName = "pleaseignore";
	private static String dbUser = "pleaseignore";
	private static String dbPass = "testalliance";
	
	public static void main(String[] args) {
		System.out.println("Sklullus' Killboard Analyser");
		Resources r = new Resources(dbHost, dbName, dbUser, dbPass);
		
		int dredditID = r.getCorpIdByName("Dreddit"); 
		System.out.println("Dreddit ID: " + dredditID);
		String dredditName = r.getCorpNameByID(dredditID);
		System.out.println("Returned Corp Name: " + dredditName);
		if (dredditName.equals("Dreddit")) {
			System.out.println("It matches");
		}
		
		System.out.println("\nBegin actual calculations now");
		
		
	}
	
}

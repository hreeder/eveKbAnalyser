package com.pleaseignore.killboardAnalyser;

import java.util.ArrayList;

public class AnalyseKillboard {

	private static String dbHost = "gandalf.pkservers.co.uk";
	private static String dbName = "pleaseignore";
	private static String dbUser = "pleaseignore";
	private static String dbPass = "testalliance";
	
	public static void main(String[] args) {
		System.out.println("Sklullus' Killboard Analyser");
		Resources r = new Resources(dbHost, dbName, dbUser, dbPass);
		
		ArrayList<Kill> kills; 
		kills = r.getKillmailsBetweenDates("2012-11-01 00:00:00", "2012-11-01 12:00:00");
		
		for (Kill k : kills) {
			System.out.println("Kill Details:");
			System.out.println("\tVictim: " + k.victim.name);
			System.out.println("\tVictim Corp: " + k.victim.corp.name);
			System.out.println("\tVictim Alliance: " + k.victim.corp.alliance.name);
			System.out.println("\tShip Class: " + k.shipClass);
			System.out.println("\tInvolved: " + k.involved.size());
		}
	}
	
}

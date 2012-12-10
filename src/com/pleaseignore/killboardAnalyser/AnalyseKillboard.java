package com.pleaseignore.killboardAnalyser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AnalyseKillboard {

	private static String dbHost = "gandalf.pkservers.co.uk";
	private static String dbName = "pleaseignore";
	private static String dbUser = "pleaseignore";
	private static String dbPass = "testalliance";
	
	public static void main(String[] args) {
		System.out.println("Sklullus' Killboard Analyser");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		Date start = new Date();
		System.out.println("Run Start: " + dateFormat.format(start));
		
		Resources r = new Resources(dbHost, dbName, dbUser, dbPass);
		
		ArrayList<Kill> kills; 
		kills = r.getKillmailsBetweenDates("2012-11-01 00:00:00", "2012-11-01 00:10:00");
		
		for (Kill k : kills) {
			System.out.println("Kill Details:");
			System.out.println("\tVictim: " + k.victim.name);
			System.out.println("\tVictim Corp: " + k.victim.corp.name);
			System.out.println("\tVictim Alliance: " + k.victim.corp.alliance.name);
			System.out.println("\tShip Class: " + k.shipClass);
			System.out.println("\tInvolved: " + k.involved.size());
		}
		
		Date end = new Date();
		System.out.println("Run Start: " + dateFormat.format(end));
		long difference = end.getTime() - start.getTime();
		System.out.println("Time Taken: " + (difference/1000)/60 + " minutes.");
	}
	
}

package com.pleaseignore.killboardAnalyser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
		kills = r.getKillmailsBetweenDates("2012-11-01 00:00:00", "2012-12-01 00:00:00");
		
		//HashMap of a HashMap to hold corps, pilots and number of KMs they appear on
		HashMap<Integer, HashMap<Integer, Integer>> outer = new HashMap<Integer, HashMap<Integer, Integer>>();
		
		for (Kill k : kills) {
			System.out.println("Kill Details:");
			System.out.println("\tVictim: " + k.victim.name);
			System.out.println("\tVictim Corp: " + k.victim.corp.name);
			System.out.println("\tVictim Alliance: " + k.victim.corp.alliance.name);
			System.out.println("\tShip Class: " + k.shipClass);
			System.out.println("\tInvolved: " + k.involved.size());
			
			for (Player p : k.involved) {
				if (!outer.containsKey(p.corp.corpKbID)) {
					outer.put(p.corp.corpKbID, new HashMap<Integer, Integer>());
				} else {
					if (!outer.get(p.corp.corpKbID).containsKey(p.playerKbID)) {
						outer.get(p.corp.corpKbID).put(p.playerKbID, 1);
					} else {
						int oldNoKills = outer.get(p.corp.corpKbID).get(p.playerKbID);
						outer.get(p.corp.corpKbID).put(p.playerKbID, oldNoKills + 1);
					}
				}
			}
		}
		
		
		// What we're going to do here is look at each corp and add up how many kills their players have been on in total
		// I'm not 100% sure if this is a correct metric to look at, but what the hey, it's something!
		HashMap<Integer, Integer> CorpTotalKMs = new HashMap<Integer, Integer>();
		
		for (int c : outer.keySet()) {
			String alliance = r.getCorpByID(c).alliance.name;
			if (alliance.equals("Test Alliance Please Ignore") || alliance.equals("Test Friends Please Ignore")) {
				int rollingTotal = 0;
				for (int p : outer.get(c).keySet()) {
					rollingTotal += outer.get(c).get(p);
				}
				CorpTotalKMs.put(c, rollingTotal);
			}
		}
		
		HashMap<Integer, Integer> CorpAverageKMs = new HashMap<Integer, Integer>();
		
		for (int c : outer.keySet()) {
			String alliance = r.getCorpByID(c).alliance.name;
			if (alliance.equals("Test Alliance Please Ignore") || alliance.equals("Test Friends Please Ignore")) {
				
			}
		}
		
		Date end = new Date();
		System.out.println("Run End: " + dateFormat.format(end));
		long difference = end.getTime() - start.getTime();
		System.out.println("Time Taken: " + (difference/1000)/60 + " minutes.");
		System.out.println("Number of kills: " + kills.size() + ".");
	}
	
}

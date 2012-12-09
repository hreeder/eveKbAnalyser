package com.pleaseignore.killboardAnalyser;

import java.util.ArrayList;

public class Kill {
	public int killID;
	public Player victim;
	public String shipClass;
	public ArrayList<Player> involved;
	
	public Kill(int _killID, int victimID, int shipID, Resources r) {
		killID = _killID;
		victim = new Player(r.getPlayerByID(victimID), r);
		
		System.out.println("DEBUG: New Kill Created.");
		
		shipClass = r.getShipClassByShipID(shipID);
		involved = r.getInvolvedPeopleFromKillmail(killID);
	}
}

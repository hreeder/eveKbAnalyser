package com.pleaseignore.killboardAnalyser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Kill {
	public int killID;
	public Player victim;
	public String shipClass;
	public ArrayList<Player> involved;
	
	public Kill(int _killID, int victimID, int shipID, Resources r) {
		killID = _killID;
		victim = new Player(r.getPlayerByID(victimID), r);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date now = new Date();
		System.out.println(dateFormat.format(now) + " DEBUG: New Kill Created.");
		
		shipClass = r.getShipClassByShipID(shipID);
		involved = r.getInvolvedPeopleFromKillmail(killID);
	}
}

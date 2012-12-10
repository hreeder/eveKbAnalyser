package com.pleaseignore.killboardAnalyser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Player {
	public String name;
	public Corporation corp;
	public int playerCCPID;
	public int playerKbID;
		
	public Player(String _name, int corpID, int ccpID, int kbID, Resources r) {
		name = _name;
		corp = new Corporation(r.getCorpByID(corpID), r);
		playerCCPID = ccpID;
		playerKbID = kbID;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date now = new Date();
		System.out.println(dateFormat.format(now) + " DEBUG: New Player Created");
	}	
}

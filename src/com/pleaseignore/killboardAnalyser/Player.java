package com.pleaseignore.killboardAnalyser;

public class Player {
	public String name;
	public String corpName;
	public String allianceName;
	public int ccpID;
	public int kbID;
	
	private Resources r;
	
	public Player(int _kbID, Resources _r) {
		kbID = _kbID;
		r = _r;
	}	
}

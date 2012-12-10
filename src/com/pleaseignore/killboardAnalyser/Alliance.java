package com.pleaseignore.killboardAnalyser;

public class Alliance {
	public String name;
	public int allianceCCPID;
	public int allianceKbID;
	
	public Alliance(String _name, int ccpID, int kbID) {
		name = _name;
		allianceCCPID = ccpID;
		allianceKbID = kbID;
	}
}

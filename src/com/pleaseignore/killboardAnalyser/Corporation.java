package com.pleaseignore.killboardAnalyser;

public class Corporation {
	public String name;
	public int corpKbID;
	public int corpCCPID;
	public int allianceKbID;
	public Alliance alliance;
	
	public Corporation(String _name, int kbID, int ccpID, int allID, Resources r) {
		name = _name;
		corpKbID = kbID;
		corpCCPID = ccpID;
		allianceKbID = allID;
		
		alliance = r.getAllianceByID(allianceKbID);
	}
}

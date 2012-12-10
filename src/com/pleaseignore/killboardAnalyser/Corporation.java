package com.pleaseignore.killboardAnalyser;

import java.sql.ResultSet;

public class Corporation {
	public String name;
	public int corpKbID;
	public int corpCCPID;
	public int allianceKbID;
	public Alliance alliance;
	
	public Corporation(ResultSet corp, Resources r) {
		try {
			while (corp.next()) {
				name = corp.getString("crp_name");
				corpKbID = corp.getInt("crp_id");
				corpCCPID = corp.getInt("crp_external_id");
				allianceKbID = corp.getInt("crp_all_id");
			}
		} catch (Exception e) {}
		
		alliance = r.getAllianceByID(allianceKbID);
	}
}

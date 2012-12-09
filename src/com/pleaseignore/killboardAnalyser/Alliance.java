package com.pleaseignore.killboardAnalyser;

import java.sql.ResultSet;

public class Alliance {
	public String name;
	public int allianceCCPID;
	public int allianceKbID;
	
	public Alliance(ResultSet alliance) {
		try {
			while (alliance.next()) {
				name = alliance.getString("all_name");
				allianceCCPID = alliance.getInt("all_external_id");
				allianceKbID = alliance.getInt("all_id");
			}
		} catch (Exception e) {}
	}
}

package com.pleaseignore.killboardAnalyser;

import java.sql.ResultSet;

public class Player {
	public String name;
	public Corporation corp;
	public int playerCCPID;
	public int playerKbID;
		
	public Player(ResultSet player, Resources r) {
		try {
			while (player.next()) {
				name = player.getString("plt_name");
				corp = new Corporation(r.getCorpByID(player.getInt("plt_crp_id")), r);
				playerCCPID = player.getInt("plt_externalid");
				playerKbID = player.getInt("plt_id");
			}
		} catch(Exception e) {}
		
	}	
}

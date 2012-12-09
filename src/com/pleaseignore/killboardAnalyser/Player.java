package com.pleaseignore.killboardAnalyser;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date now = new Date();
		System.out.println(dateFormat.format(now) + " DEBUG: New Player Created");
	}	
}

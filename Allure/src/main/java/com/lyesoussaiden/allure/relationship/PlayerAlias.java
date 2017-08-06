package com.lyesoussaiden.allure.relationship;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerAlias {
	
	public Map<UUID, String> customPlayerName = new HashMap<UUID, String>();
	public String getAlias(Player player) {
		return customPlayerName.get(player.getUniqueId());
	}
	
	//Sets the player's alias. If the name is to only ever be applied once, set last param. to be true.
	public void setAlias(Player player, String name, boolean onlyOnce) {
		if(onlyOnce && customPlayerName.containsKey(player.getUniqueId())) {
			return;
		}
		customPlayerName.put(player.getUniqueId(), name);
	}
}

package com.lyesoussaiden.allure.relationship;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.lyesoussaiden.allure.utils.AllureIO;
import com.lyesoussaiden.allure.utils.IAllureIO;

public class PlayerAlias implements IAllureIO{
	
	public PlayerAlias(AllureIO allureIO) {
		allureIO.registerConfigObject(this);
	}
	
	public Map<UUID, String> customPlayerName = new HashMap<UUID, String>();
	
	//Gets the player's alias.
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

	@Override
	public void loadData(FileConfiguration config) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void saveData(FileConfiguration config) {
		// TODO Auto-generated method stub
		
	}
}

package com.lyesoussaiden.allure.relationship;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.lyesoussaiden.allure.utils.AllureIO;
import com.lyesoussaiden.allure.utils.IAllureIO;

public class PlayerAlias implements IAllureIO{
	
	private String setNameMessage;
	
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
		//If onlyOnce is set to true, make sure there are no custom player names in map already.
		if(onlyOnce && customPlayerName.containsKey(player.getUniqueId())) {
			return;
		}
		
		//Format and send message.
		player.sendMessage("§2§O" + new String(setNameMessage).replace("_1", name));
		
		//Add message to custom list.
		customPlayerName.put(player.getUniqueId(), name);
	}

	@Override
	public void loadData(FileConfiguration config) {
				
		setNameMessage = config.getString("lang.setNameMessage");
		
		//Load map.
		customPlayerName = new HashMap<UUID, String>();
		ConfigurationSection uuidNameConfigSect = config.getConfigurationSection("save.names");
		if(uuidNameConfigSect != null) {
			Set<String> users = uuidNameConfigSect.getKeys(false);
			if(!users.isEmpty()) {
				for(String user : users) {
					customPlayerName.put(UUID.fromString(user), config.getString("save.names." + user));
				}
			}
		}
	}
	
	@Override
	public void saveData(FileConfiguration config) {
		//Save current map.
		for(UUID playerID : customPlayerName.keySet()) {
			config.set("save.names." + playerID.toString(), customPlayerName.get(playerID));
		}
		
	}
}

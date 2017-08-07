package com.lyesoussaiden.allure.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class AllureMetrics implements IAllureIO{
	
	private int maximumChatDistance = 20;
	
	public AllureMetrics(AllureIO allureIO) {
		allureIO.registerConfigObject(this);
	}
	
	public boolean arePlayersInChatDistance(Player firstParty, Player secondParty) {
		return firstParty.getLocation().distance(secondParty.getLocation()) < getMaximumChatDistance();
	}
	
	public int getMaximumChatDistance() {
		return maximumChatDistance;
	}

	@Override
	public void saveData(FileConfiguration config) {
		maximumChatDistance = config.getInt("metrics.maximumChatDistance");		
	}

	@Override
	public void loadData(FileConfiguration config) {
		config.set("metrics.maximumChatDistance", getMaximumChatDistance());		
	}
	
}

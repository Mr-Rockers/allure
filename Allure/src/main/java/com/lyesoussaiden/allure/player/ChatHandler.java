package com.lyesoussaiden.allure.player;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.lyesoussaiden.allure.relationship.PlayerAlias;
import com.lyesoussaiden.allure.relationship.PlayerRelationshipHandler;
import com.lyesoussaiden.allure.relationship.RelationshipStatus;
import com.lyesoussaiden.allure.utils.AllureIO;
import com.lyesoussaiden.allure.utils.AllureMetrics;
import com.lyesoussaiden.allure.utils.IAllureIO;

public class ChatHandler implements Listener, IAllureIO{
	
	public AllureMetrics allureMetrics;
	public PlayerRelationshipHandler playerRelationships;
	public PlayerAlias playerAlias;
	
	private String announceNameToSender, announceNameToListeners, announceNameFail;
	boolean isLoaded = false;
	
	public ChatHandler(AllureIO allureIO, AllureMetrics allureMetrics, PlayerRelationshipHandler playerRelationships, PlayerAlias playerAlias) {
		allureIO.registerConfigObject(this);
		this.allureMetrics = allureMetrics;
		this.playerRelationships = playerRelationships;
		this.playerAlias = playerAlias;
	}
	
	public String getNamePrefix (Player sender, Player recipient) {
		String prefix = "";
		if(sender != recipient){
			prefix = "§7";
			
			if(playerRelationships.getRelationship(playerRelationships.getRelationshipPlayer(recipient, true), sender) == RelationshipStatus.NONE) {
				prefix += "[???] ";
			}
			else
			{
				String playerName = playerAlias.getAlias(sender);
				if(playerName == null) {
					playerName = sender.getPlayerListName();
				}
				
				prefix += ("[" + playerName + "] ");
			}
		}
		return prefix;
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		for(Player p : event.getPlayer().getWorld().getPlayers()) {
			
			if(allureMetrics.arePlayersInChatDistance(event.getPlayer(), p)) { //If the player's position is within X to the iterated players position.
				
				p.sendMessage(getNamePrefix(p, event.getPlayer()) + event.getMessage());
			
			}
		}
	}
	
	public void announceName(Player source) {
		List<Player> affectedPlayers = playerRelationships.announceRelationship(source, allureMetrics.getMaximumChatDistance(), RelationshipStatus.ACQUAINTANCE);
		if(affectedPlayers.isEmpty()) {
			source.sendMessage(announceNameFail);
		}
		else {
			source.sendMessage(announceNameToSender);
			for(Player p : affectedPlayers) {
				source.sendMessage( getNamePrefix(source, p) + announceNameToListeners );
			}
		}
	}
	
	@Override
	public void loadData(FileConfiguration config) {
		this.announceNameToSender = config.getString("lang.announceNameToSender");
		this.announceNameToListeners = config.getString("lang.announceNameToListeners");
		this.announceNameFail = config.getString("lang.announceNameFail");
	}
	
	@Override
	public void saveData(FileConfiguration config) {
		// TODO Auto-generated method stub
		
	}
	
}

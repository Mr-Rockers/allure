package com.lyesoussaiden.allure.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.lyesoussaiden.allure.relationship.PlayerAlias;
import com.lyesoussaiden.allure.relationship.PlayerRelationships;
import com.lyesoussaiden.allure.relationship.RelationshipStatus;
import com.lyesoussaiden.allure.utils.AllureMetrics;

public class ChatHandler implements Listener{
	
	public AllureMetrics allureMetrics;
	public PlayerRelationships playerRelationships;
	public PlayerAlias playerAlias;
	
	public ChatHandler(AllureMetrics allureMetrics, PlayerRelationships playerRelationships, PlayerAlias playerAlias) {
		this.allureMetrics = allureMetrics;
		this.playerRelationships = playerRelationships;
		this.playerAlias = playerAlias;
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		for(Player p : event.getPlayer().getWorld().getPlayers()) {
			
			if(allureMetrics.arePlayersInChatDistance(event.getPlayer(), p)) { //If the player's position is within X to the iterated players position.
			
				//Set message colour and name.
				String prefix;
				if(p == event.getPlayer()) {
					prefix = "";
				}
				else {
					prefix = "§7";
					
					if(playerRelationships.getRelationship(event.getPlayer(), p) == RelationshipStatus.NONE) {
						prefix += "[???] ";
					}
					else
					{
						String playerName = playerAlias.getAlias(p);
						if(playerName == null) {
							playerName = p.getPlayerListName();
						}
						
						prefix += ("[" + playerName + "] ");
					}
				}
				
				p.sendMessage(prefix + event.getMessage());
			
			}
		}
	}
	
}

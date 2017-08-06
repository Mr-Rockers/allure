package com.lyesoussaiden.allure.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.lyesoussaiden.allure.relationship.PlayerAlias;
import com.lyesoussaiden.allure.relationship.PlayerRelationships;
import com.lyesoussaiden.allure.relationship.RelationshipStatus;

public class ChatHandler implements Listener{
	
	public PlayerRelationships playerRelationships;
	public PlayerAlias playerAlias;
	
	public ChatHandler(PlayerRelationships playerRelationships, PlayerAlias playerAlias) {
		this.playerRelationships = playerRelationships;
		this.playerAlias = playerAlias;
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		for(Player p : event.getPlayer().getWorld().getPlayers()) {
			
			if(event.getPlayer().getLocation().distance(p.getLocation()) < 20) { //If the player's position is within X to the iterated players position.
			
				//Set message colour and name.
				String prefix;
				if(p == event.getPlayer()) {
					prefix = "";
				}
				else {
					prefix = "§7";
					
					if(playerRelationships.getRelationship(event.getPlayer().getUniqueId(), p.getUniqueId()) == RelationshipStatus.NONE) {
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

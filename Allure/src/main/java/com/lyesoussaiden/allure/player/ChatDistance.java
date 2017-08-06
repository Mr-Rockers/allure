package com.lyesoussaiden.allure.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatDistance implements Listener{

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		for(Player p : event.getPlayer().getWorld().getPlayers()) {
			
			String prefix;
			if(p == event.getPlayer()) {
				prefix = "";
			}
			else {
				prefix = "§7[???] ";
			}
			
			p.sendMessage(prefix + event.getMessage());
		}
	}
	
}

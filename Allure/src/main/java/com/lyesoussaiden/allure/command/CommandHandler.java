package com.lyesoussaiden.allure.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lyesoussaiden.allure.player.ChatHandler;

public class CommandHandler{

	ChatHandler chatHandler;
	public CommandHandler(ChatHandler chatHandler) {
		this.chatHandler = chatHandler;
	}
	
	public boolean handleCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("tN") || command.getName().equalsIgnoreCase("tellname")) {
			chatHandler.announceName((Player)sender);
		}
		
		return false;
	}
	
}

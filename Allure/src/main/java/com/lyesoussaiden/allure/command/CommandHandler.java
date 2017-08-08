package com.lyesoussaiden.allure.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lyesoussaiden.allure.chat.ChatHandler;
import com.lyesoussaiden.allure.relationship.PlayerAlias;

public class CommandHandler{

	ChatHandler chatHandler;
	PlayerAlias playerAlias;
	public CommandHandler(ChatHandler chatHandler, PlayerAlias playerAlias) {
		this.chatHandler = chatHandler;
		this.playerAlias = playerAlias;
	}
	
	public boolean handleCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("tN") || command.getName().equalsIgnoreCase("tellname")) {
			chatHandler.announceName((Player)sender);
		}
		else if(command.getName().equalsIgnoreCase("name")) {
			playerAlias.setAlias((Player)sender, args[0], true);
		}
		else {
			return false;
		}
		return true;
	}
	
}

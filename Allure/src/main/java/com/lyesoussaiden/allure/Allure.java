package com.lyesoussaiden.allure;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.lyesoussaiden.allure.command.CommandHandler;
import com.lyesoussaiden.allure.player.ChatHandler;
import com.lyesoussaiden.allure.relationship.PlayerAlias;
import com.lyesoussaiden.allure.relationship.PlayerRelationships;
import com.lyesoussaiden.allure.utils.Constants;

public final class Allure extends JavaPlugin implements Listener{
	public static String PluginApiName, InitialMOTD;
	
	PlayerAlias playerAlias;
	PlayerRelationships playerRelationships;
	ChatHandler chatDistance = new ChatHandler(playerRelationships, playerAlias);
	CommandHandler commandHandler;
	
	@Override
	public void onEnable() {
		InitialMOTD = getServer().getMotd();
		PluginApiName = (getLogger().getClass().getName().toLowerCase().contains("spigot") ? "Spigot " :
			getLogger().getClass().getName().toLowerCase().contains("bukkit") ? "Bukkit " : "");
		
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(chatDistance, this);
		
		getLogger().info("~~~~~~~~~~~~~~~~~~~~");
		getLogger().info(Constants.NAME + " Created by " + Constants.AUTHOR);
		getLogger().info("Built for:  " + Constants.TARGET);
		getLogger().info("Running on: " + PluginApiName + Bukkit.getBukkitVersion());
		getLogger().info("~~~~~~~~~~~~~~~~~~~~");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Allure has been disabled.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return commandHandler.handleCommand(sender, command, label, args);
	}

	@EventHandler
	public void onLogin (PlayerLoginEvent event) {
		getLogger().log(Level.INFO, "Player " + event.getPlayer().getName() + " is logging in to the server.");
	}
	
}

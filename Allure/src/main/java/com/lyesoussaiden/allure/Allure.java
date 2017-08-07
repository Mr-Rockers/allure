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
import com.lyesoussaiden.allure.utils.AllureConstants;
import com.lyesoussaiden.allure.utils.AllureIO;
import com.lyesoussaiden.allure.utils.AllureMetrics;

public final class Allure extends JavaPlugin implements Listener{
	public static String PluginApiName, InitialMOTD;
	
	//"IO Enabled" Classes
	AllureIO allureIO = new AllureIO(this.getConfig());
	AllureMetrics allureMetrics = new AllureMetrics(allureIO);
	PlayerAlias playerAlias = new PlayerAlias(allureIO);
	PlayerRelationships playerRelationships = new PlayerRelationships(allureIO);
	
	//Functional Classes
	ChatHandler chatDistance = new ChatHandler(allureMetrics, playerRelationships, playerAlias);
	CommandHandler commandHandler;
	
	@Override
	public void onEnable() {
		InitialMOTD = getServer().getMotd();
		PluginApiName = (getLogger().getClass().getName().toLowerCase().contains("spigot") ? "Spigot " :
			getLogger().getClass().getName().toLowerCase().contains("bukkit") ? "Bukkit " : "");
		
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(chatDistance, this);
		
		getLogger().info("~~~~~~~~~~~~~~~~~~~~");
		getLogger().info(AllureConstants.NAME + " Created by " + AllureConstants.AUTHOR);
		getLogger().info("Built for:  " + AllureConstants.TARGET);
		getLogger().info("Running on: " + PluginApiName + Bukkit.getBukkitVersion());
		getLogger().info("Loading configuration files...");
		this.saveDefaultConfig();
		allureIO.readAll();
		getLogger().info("Loaded.");
		getLogger().info("~~~~~~~~~~~~~~~~~~~~");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("~~~~~~~~~~~~~~~~~~~~");
		getLogger().info("Saving configuration files...");
		allureIO.writeAll();
		this.saveConfig();
		getLogger().info("Saved.");
		getLogger().info("~~~~~~~~~~~~~~~~~~~~");
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

package com.lyesoussaiden.allure;

import java.util.logging.Level;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Allure extends JavaPlugin implements Listener{

	@Override
	public void onEnable() {
		getLogger().info("Allure has been enabled.");
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Allure has been disabled.");
	}
	
	@EventHandler
	public void onLogin (PlayerLoginEvent event) {
		getLogger().log(Level.INFO, "Player " + event.getPlayer().getName() + " is logging in to the server.");
	}
	
}

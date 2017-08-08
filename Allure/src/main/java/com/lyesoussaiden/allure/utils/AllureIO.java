package com.lyesoussaiden.allure.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class AllureIO {
		
	public FileConfiguration config;
	
	public List<IAllureIO> configObjects = new ArrayList<IAllureIO>();
	
	public AllureIO(FileConfiguration config) {
		this.assignConfig(config);
	}
	
	public void registerConfigObject(IAllureIO configObject) {
		configObjects.add(configObject);
	}
	
	public void readAll() {
		for(IAllureIO configObject : configObjects) {
			configObject.loadData(config);
		}
	}
	
	public void writeAll() {
		for(IAllureIO configObject : configObjects) {
			configObject.saveData(config);
		}
	}
	
	public void assignConfig(FileConfiguration config) {
		this.config = config;
	}
	
}

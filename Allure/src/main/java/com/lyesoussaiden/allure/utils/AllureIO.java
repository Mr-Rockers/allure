package com.lyesoussaiden.allure.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class AllureIO {
		
	public FileConfiguration defaultConfig;
	public AllureIO(FileConfiguration defaultConfig) {
		this.defaultConfig = defaultConfig;
	}
	
	public List<IAllureIO> configObjects = new ArrayList<IAllureIO>();
	
	public void registerConfigObject(IAllureIO configObject) {
		configObjects.add(configObject);
	}
	
	public void readAll() {
		for(IAllureIO configObject : configObjects) {
			configObject.loadData(defaultConfig);
		}
	}
	
	public void writeAll() {
		for(IAllureIO configObject : configObjects) {
			configObject.saveData(defaultConfig);
		}
	}
	
}

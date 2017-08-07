package com.lyesoussaiden.allure.utils;

import org.bukkit.configuration.file.FileConfiguration;

/*ALSO REFERRED TO AS A CONFIG OBJECT*/

public interface IAllureIO {
	public void saveData(FileConfiguration config);
	public void loadData(FileConfiguration config);
}

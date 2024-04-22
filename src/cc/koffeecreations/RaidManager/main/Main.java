package cc.koffeecreations.RaidManager.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import cc.koffeecreations.BossFights.EldritchGuardianBossFight;

public class Main extends JavaPlugin {

	public FileConfiguration config;
	
	 @Override
	    public void onEnable() {
		 config = getConfig();
		 
		 
		 
	 }
	 
	 
	 public void spawn(String Name, Location L) {
		 
		 if (Name.equalsIgnoreCase("eldrich")) {		 
			 //This spawns the EldrichGuardian at the location specified.
			 new EldritchGuardianBossFight(this).summonBoss(L);
		 }
		 
		 
	 }
	
}

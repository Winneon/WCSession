package com.github.winneonsword.WCS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MainWCS extends JavaPlugin {
	
	MainWCS plugin;
	
	public static File configFile;
	public static FileConfiguration ConfigWCS;
	
	public MainWCS(){
		
		// Gotta love dem empty lines.
		
	}
	
	@Override
	public void onEnable(){
		
		configFile = new File(getDataFolder(), "config.yml");
		
		try {
			
			firstRun();
			
		} catch (Exception e){
			
			e.printStackTrace();
			
		}
		
		ConfigWCS = new YamlConfiguration();
		
		loadYMLs();
		
		plugin = this;
		
		plugin.getCommand("wcs").setExecutor(new CommandWCS(this));
		getLogger().info("WCSession has been enabled! Meow!");
		
	}
	
	@Override
	public void onDisable(){
		
		getLogger().info("WCSession has been disabled! *cry*");
		
	}
	
	private void copy(InputStream in, File file){
		
		try {
			
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			
			while ((len = in.read(buf)) > 0){
				
				out.write(buf, 0, len);
				
			}
			
			out.close();
			in.close();
			
		} catch (Exception e){
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void saveYMLs(){
		
		try {
			
			ConfigWCS.save(configFile);
			
		} catch (IOException e){
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void loadYMLs(){
		
		try {
			
			ConfigWCS.load(configFile);
			
		} catch (Exception e){
			
			e.printStackTrace();
			
		}
		
	}
	
	private void firstRun() throws Exception {
		
		if (!(configFile.exists())){
			
			configFile.getParentFile().mkdirs();
			copy(getResource("config.yml"), configFile);
			
		}
		
	}
	
}

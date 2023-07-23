package fr.skytryx.ultihc.utils;

import org.bukkit.Bukkit;

import net.md_5.bungee.api.ChatColor;

public class Chronometer {
	
	public static Integer chronometer = -1;
	
	public static Integer get() {
		return chronometer;
	}

	public static void set(Integer newchronometer) {
		chronometer = newchronometer;
	}

	public static void start() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("UltiHC"),() -> {
			   set(get()+1);
			   if(Integer.parseInt(Settings.get("FinalHeal"))*60-get() == 0) {
				   Bukkit.broadcastMessage(ChatColor.AQUA+"FinalHeal has been deployed!");
				   Fill.get().forEach(p->{
					   p.setHealth(p.getMaxHealth());
					   p.setSaturation(20);
				   });
			   }
			   if(Integer.parseInt(Settings.get("PvP"))*60-get() == 0) Bukkit.broadcastMessage(ChatColor.AQUA+"PvP has been enabled!");
			   if(Integer.parseInt(Settings.get("FirstShrink"))*60-get() == 0) {
				   Settings.set("Border", String.valueOf(Integer.parseInt(Settings.get("Border"))/2));
				   Bukkit.getOnlinePlayers().forEach(p ->{
					   if(p.getLocation().getX() > Integer.parseInt(Settings.get("Border"))/2 || p.getLocation().getZ() > Integer.parseInt(Settings.get("Border"))/2
						  || p.getLocation().getX() < -Integer.parseInt(Settings.get("Border"))/2 || p.getLocation().getZ() < -Integer.parseInt(Settings.get("Border"))/2) {
						   UHCStart.ChooseSpawn(p);
					   }
				   });
					   Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("UltiHC"), ()->{
						   Bukkit.getWorld("UHC").getWorldBorder().setSize(Integer.parseInt(Settings.get("Border"))*2);
						   Bukkit.getWorld("UHC_nether").getWorldBorder().setSize((Integer.parseInt(Settings.get("Border"))*2)/8);
						   if(Integer.parseInt(Settings.get("Border")) > 25)Settings.set("FirstShrink", String.valueOf(Integer.parseInt(Settings.get("FirstShrink"))+Integer.parseInt(Settings.get("ShrinkTime"))));
						   Bukkit.broadcastMessage("§bThe Border has shrunk to §6"+ Settings.get("Border"));
				   });
			   }
			}, 0L , 20L);
		}

	public static String format(Integer chronometer) {
		int seconds = chronometer;
		int hours = Math.floorDiv(seconds, 3600);
		seconds-= hours*3600;
		int minutes= Math.floorDiv(seconds, 60);
		seconds-= minutes*60;
		if(hours != 0) return (String.format("%02d", hours)+":"+String.format("%02d", minutes)+":"+String.format("%02d", seconds));
		else return (String.format("%02d", minutes)+":"+String.format("%02d", seconds));
		}
}

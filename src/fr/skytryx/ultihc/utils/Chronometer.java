package fr.skytryx.ultihc.utils;

import org.bukkit.Bukkit;

import fr.skytryx.ultihc.scenarios.NoClean;
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
			   if(Integer.parseInt(Settings.get("PvP"))*60-get() == 0) {
				   Bukkit.broadcastMessage(ChatColor.AQUA+"PvP has been enabled!");
			   }
			   Bukkit.getOnlinePlayers().forEach(p ->{if(NoClean.NoCleanList.containsKey(p)){
				   NoClean.NoCleanList.remove(p);
				   p.sendMessage(ChatColor.RED+"You are no longer in NoClean mode!");   
				   }
			   });
			}, 0L , 20L);
		}

	public static String format(Integer chronometer) {
		int seconds = chronometer;
		int hours = Math.floorDiv(seconds, 3600);
		seconds-= hours*3600;
		int minutes= Math.floorDiv(seconds, 60);
		seconds-= minutes*60;
		return (String.format("%02d", hours)+":"+String.format("%02d", minutes)+":"+String.format("%02d", seconds));
		}
}

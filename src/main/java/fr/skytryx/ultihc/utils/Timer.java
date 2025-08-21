package fr.skytryx.ultihc.utils;

import org.bukkit.Bukkit;

public class Timer {
	
	public static Integer timer = -1;

	public static Integer get() {
		return timer;
		}

	public static void set(Integer newtimer) {
		timer = newtimer;
		}

	public static void start() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("UltiHC"),() -> {
			   Timer.set(Timer.get()-1);
			   if(Timer.get() == 0) {
				   Fill.fillcount = Fill.get().size();
				   Bukkit.getScheduler().cancelAllTasks();
					if(Settings.get("Monster Spawning").equals("true")) Bukkit.getWorld("UHC").setMonsterSpawnLimit(0);
					Bukkit.getWorld("UHC").getWorldBorder().setSize(Integer.parseInt(Settings.get("Border"))*2);
					Bukkit.getWorld("UHC_nether").getWorldBorder().setSize((Integer.parseInt(Settings.get("Border"))*2)/8);
				   UHCStart.Scatter(Fill.get());
				   Chronometer.start();
				   KillCount.setKillCount(Fill.get());
			   }
			}, 0L , 20L);
	}

	public static String format() {
		int seconds = timer;
		int hours = Math.floorDiv(seconds, 3600);
		seconds-= hours*3600;
		int minutes= Math.floorDiv(seconds, 60);
		seconds-= minutes*60;
		return (String.format("%02d", hours)+":"+String.format("%02d", minutes)+":"+String.format("%02d", seconds));
	}
}

package fr.skytryx.ultihc.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Scoreboard;

public class Scoreboards {

	public static void PreGame(Player player) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("UltiHC"),() -> {
		    ScoreboardManager manager = Bukkit.getScoreboardManager();
		    Scoreboard scoreboard = manager.getNewScoreboard();
	        Objective objective = scoreboard.registerNewObjective("Title", "dummy");
	        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	        
	        objective.setDisplayName("§a§lUltiHC");
		    objective.getScore("  ").setScore(4+Settings.scenariolist.size());
		    objective.getScore("§aStarting: §b" + Timer.format()).setScore(3+Settings.scenariolist.size());
		    objective.getScore(" ").setScore(2+Settings.scenariolist.size());
		    objective.getScore("§aScenarios").setScore(1+Settings.scenariolist.size());
		    
		    for(int x = 0; x < Settings.scenariolist.size(); x++) {
		    	objective.getScore("§2 - §b"+ Settings.scenariolist.get(x)).setScore(Settings.scenariolist.size()-x);
		    }
	        
	        player.setScoreboard(scoreboard);
		}, 0L , 10L);
	}
	
	public static void Game(Player player) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("UltiHC"),() -> {
		    ScoreboardManager manager = Bukkit.getScoreboardManager();
		    Scoreboard scoreboard = manager.getNewScoreboard();
	        Objective objective = scoreboard.registerNewObjective("Title", "dummy");
	        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	        
	        objective.setDisplayName("§a§lUltiHC");
		    objective.getScore("   ").setScore(10);
		    objective.getScore("§3§lTimers").setScore(9);
		    objective.getScore("§aGameTime: §b" + Chronometer.format(Chronometer.get())).setScore(8);
		    if(Integer.parseInt(Settings.get("PvP"))*60-Chronometer.get() > 0) {
		    	objective.getScore("§aPvP: §b"+Chronometer.format(Integer.parseInt(Settings.get("PvP"))*60-Chronometer.get())).setScore(7);
		    }
		    if(Integer.parseInt(Settings.get("FinalHeal"))*60-Chronometer.get() > 0) {
		    	objective.getScore("§aFinalHeal: §b"+Chronometer.format(Integer.parseInt(Settings.get("FinalHeal"))*60-Chronometer.get())).setScore(6);
		    }
		    objective.getScore("  ").setScore(5);
		    objective.getScore("§3§lGame Stats").setScore(4);
		    objective.getScore("§aPlayers: §b" + Fill.get().size()+"/"+ Fill.fillcount).setScore(3);
		    objective.getScore("§aBorder: §b"+Settings.get("Border")).setScore(2);
		    objective.getScore("§aKills: §b" + "0").setScore(1);
		    objective.getScore(" ").setScore(0);
	        player.setScoreboard(scoreboard);
		}, 20L , 10L);
	}
	
	public static void PostGame(Player player) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("UltiHC"),() -> {
		    ScoreboardManager manager = Bukkit.getScoreboardManager();
		    Scoreboard scoreboard = manager.getNewScoreboard();
	        Objective objective = scoreboard.registerNewObjective("Title", "dummy");
	        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	        
	        objective.setDisplayName("§a§lUltiHC");
		    objective.getScore("  ").setScore(10);
		    objective.getScore("§aWinner: §b" + Settings.getWinner().getDisplayName()).setScore(9);
		    objective.getScore("§aFill: §b" + Fill.fillcount.toString()).setScore(8);
		    objective.getScore(" ").setScore(1);
	        player.setScoreboard(scoreboard);
		}, 0L , 20L);
	}
	
}

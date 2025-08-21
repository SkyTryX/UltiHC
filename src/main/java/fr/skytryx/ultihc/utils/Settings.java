package fr.skytryx.ultihc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

public class Settings {

	public static Map<String, String> settingsuhc = new HashMap<String, String>();
	public static List<String> scenariolist = new ArrayList<>();
	public static Player winner;
	
	public static String get(String setting) {
		return settingsuhc.get(setting).toString();
	}
	
	public static void set(String setting, String newinput) {
		settingsuhc.put(setting, newinput);
	}

	public static void reset() {
		settingsuhc.put("Team Size", "1");
		settingsuhc.put("Border", "2000");
		settingsuhc.put("FirstShrink", "40");
		settingsuhc.put("ShrinkTime", "5");
		settingsuhc.put("Monster Spawning", "false");
		settingsuhc.put("Nether", "true");
		settingsuhc.put("PvP", "20");
		settingsuhc.put("BedBombs", "false");
		settingsuhc.put("God Apples", "false");
		settingsuhc.put("FinalHeal", "10");
		settingsuhc.put("Scenarios", scenariolist.toString());
	}

	public static void addscen(String scenario) {
		scenariolist.add(scenario);	
		settingsuhc.put("Scenarios", scenariolist.toString());
	}
	
	public static void removescen(String scenario) {
		if(scenariolist.contains(scenario)) {
		scenariolist.remove(scenario);	
		settingsuhc.put("Scenarios", scenariolist.toString());
		}
	}

	public static Player getWinner() {
		return winner;
	}
	public static void setWinner(Player newwinner) {
		winner = newwinner;
	}
}
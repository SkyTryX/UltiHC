package fr.skytryx.ultihc.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

public class KillCount {

	public static Map<Player, Integer> KillCount = new HashMap<>();
	
	public static Map<Player, Integer> getKillCount() {
		return KillCount;
	}
	
	public static void addKill(Player player) {
		if(KillCount.containsKey(player)) {
			KillCount.put(player, KillCount.get(player)+1);
		} else KillCount.put(player, 1);
	}
	
	public static void setKillCount(List<Player> fill) {
		fill.forEach(p ->{
			KillCount.put(p, 0);
		});
	}
	
	public static Integer getKill(Player player) {
		return KillCount.get(player);
	}
}

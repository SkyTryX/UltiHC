package fr.skytryx.ultihc.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Fill {

	public static List<Player> fill = new ArrayList<>();
	public static Integer fillcount;
	
	public static List<Player> get(){
		return fill;
	}
	
	public static void add(Player player){
		fill.add(player);
	}
	
	public static void remove(Player player){
		fill.remove(player);
	}
}

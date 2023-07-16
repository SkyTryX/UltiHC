package fr.skytryx.ultihc.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Biome;



public class WorldCreation {
	
	public static void CreateWorld() {
		Bukkit.getOnlinePlayers().forEach(player ->{player.kickPlayer("You have been kicked due to the map being reloaded.");});	
		if(Bukkit.getWorld("UHC") != null) Bukkit.unloadWorld("UHC", false);
		try { FileUtils.deleteDirectory(new File("UHC"));} catch (IOException e) {System.out.println("[UltiHC] UHC Folder not existing");}
		World world = new WorldCreator("UHC").createWorld();
		
		if((world.getBiome(0, 0) == Biome.DESERT || world.getBiome(0, 0) == Biome.PLAINS) && world.getBlockAt(new Location(world, 0, 62, 0)).getType() != Material.WATER) {
			System.out.println("[UltiHC] UHCWorld has been created, creating nether!");
			WorldCreator netherworld = new WorldCreator("UHC_nether");
			netherworld.environment(World.Environment.NETHER);
			netherworld.createWorld();
			System.out.println("[UltiHC] Created Nether!");
			world.setGameRuleValue("naturalRegeneration", "false");
		} else {
			System.out.println("[UltiHC] World is getting reloaded due to 0 0 not being flat");
			CreateWorld();
		}
	}
	
	public static void PregenerateWorld() {
		World world = Bukkit.getWorld("UHC");
		int border_in_chunk = Math.round(Integer.parseInt(Settings.get("Border"))/16);
		for(int a = 0; border_in_chunk > a; a++) {
			for(int b = -border_in_chunk; border_in_chunk > b; b++) {
				world.loadChunk(a, b);
			}
		}
		world.save();
		Bukkit.broadcastMessage("Map has been loaded");
	}
}
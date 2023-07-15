package fr.skytryx.ultihc.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UHCStart {

	private static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		return (int)(Math.random() * ((max - min) + 1)) + min;
	}
	
	public static void Scatter(List<Player> players) {
		Bukkit.broadcastMessage("§bStarting to scatter all players!");
		if(Settings.get("Monster Spawning").equals("true")) Bukkit.getWorld("UHC").setMonsterSpawnLimit(0);
		Bukkit.getWorld("UHC").getWorldBorder().setSize(Integer.parseInt(Settings.get("Border"))*2);
		Bukkit.getWorld("UHC_nether").getWorldBorder().setSize((Integer.parseInt(Settings.get("Border"))*2)/8);
		players.forEach(p ->{
			p.getInventory().clear();
			p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999, 128), true);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 255), true);
			p.setGameMode(GameMode.ADVENTURE);
			ChooseSpawn(p);
		});
		Bukkit.broadcastMessage("§bEvery players have been sent in the map!");
		players.forEach(p ->{
			p.removePotionEffect(PotionEffectType.SLOW);
			p.removePotionEffect(PotionEffectType.JUMP);
			p.setGameMode(GameMode.SURVIVAL);
		});
		Bukkit.getOnlinePlayers().forEach(p ->{
			Scoreboards.Game(p);
		});
	}

	public static void ChooseSpawn(Player p) {
		int x = getRandomNumberInRange(-Integer.parseInt(Settings.get("Border")), Integer.parseInt(Settings.get("Border")));
		int z = getRandomNumberInRange(-Integer.parseInt(Settings.get("Border")), Integer.parseInt(Settings.get("Border")));
		for (int y = 100; y > 58; y--) {
			Material block_spawn = Bukkit.getWorld("UHC").getBlockAt(new Location(Bukkit.getWorld("UHC"), x, y, z)).getType();
			p.sendMessage(block_spawn.toString());
			if(block_spawn == Material.STATIONARY_LAVA || block_spawn == Material.STATIONARY_WATER) {
				ChooseSpawn(p);
				return;
			}
			if(block_spawn != Material.AIR && Bukkit.getWorld("UHC").getBlockAt(new Location(Bukkit.getWorld("UHC"), x, y+1, z)).getType() == Material.AIR) {
				p.teleport(new Location(Bukkit.getWorld("UHC"), x+0.5, y+1, z+0.5));
				return;
			}
		}
		ChooseSpawn(p);
	}
}
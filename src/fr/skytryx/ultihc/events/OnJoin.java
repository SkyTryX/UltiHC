package fr.skytryx.ultihc.events;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.skytryx.ultihc.utils.Timer;
import fr.skytryx.ultihc.utils.Fill;
import fr.skytryx.ultihc.utils.GiveInv;

public class OnJoin implements Listener {
	
	@EventHandler
	public void OnPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
        final File stats = new File(Bukkit.getServer().getPluginManager().getPlugin("UltiHC").getDataFolder(), "stats.yml");
        final YamlConfiguration configstats = YamlConfiguration.loadConfiguration(stats);
        if((configstats.get(player.getUniqueId() + ".kills")) == null){
        	configstats.set(player.getUniqueId() + ".wins", 0);
        	configstats.set(player.getUniqueId() + ".elo", 1400);
        	configstats.set(player.getUniqueId() + ".kills", 0);
        	configstats.set(player.getUniqueId() + ".death", 0);
        	try {
        		configstats.save(stats);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		
		Fill.add(player);
		player.teleport(new Location(Bukkit.getWorld("world"), 0, 51, 0));
		if(player.isOp() && Timer.get() == -1) {
			GiveInv.GiveSetupInv(player);
		} else if(player.isOp()) {
			GiveInv.GiveStaffInv(player);
		}
	}
}

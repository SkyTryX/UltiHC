package fr.skytryx.ultihc.events;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.skytryx.ultihc.utils.Settings;
import fr.skytryx.ultihc.utils.Chronometer;
import fr.skytryx.ultihc.utils.Fill;
import fr.skytryx.ultihc.utils.KillCount;
import fr.skytryx.ultihc.utils.Scoreboards;

public class OnDeath implements Listener {

	
	@EventHandler
	public void Death(PlayerDeathEvent event) {
		if(Chronometer.get() != -1) {
			final File stats = new File(Bukkit.getServer().getPluginManager().getPlugin("UltiHC").getDataFolder(), "stats.yml");
	        final YamlConfiguration configstats = YamlConfiguration.loadConfiguration(stats);
			Player player = event.getEntity();
			String prefix = ChatColor.RED + player.getName() + "["+KillCount.getKill(player)+"] ";
			configstats.set(player.getUniqueId() + ".death", (Integer)configstats.get(player.getUniqueId() + ".death")+1);
			if(player.getKiller() == null) {
			    if (event.getDeathMessage().contains("was killed while trying")) {
			    event.setDeathMessage(prefix+ "killed by "+player.getKiller().getDisplayName());
				
			    } else if(event.getDeathMessage().contains("hit the ground too hard")) {
				event.setDeathMessage(prefix + "fell to his death.");
				
			    } else if (event.getDeathMessage().contains("drowned")) {
				event.setDeathMessage(prefix + "forgot to get a respiration 3 helmet");
				
				} else if (event.getDeathMessage().contains("swim in lava")) {
				event.setDeathMessage(prefix + "forgot how lava is hot");
				
				} else if (event.getDeathMessage().contains("struck by lightning")) {
				event.setDeathMessage(prefix + "somehow got struck by lightning");
				
				} else if (event.getDeathMessage().contains("blew up") || event.getDeathMessage().contains("blown up")) {
				event.setDeathMessage(prefix + "blew the hell up!");
				
				} else if (event.getDeathMessage().contains("went up in flames") || event.getDeathMessage().contains("burned to death")) {
				event.setDeathMessage(prefix + "burned to his death");
				
				} else if (event.getDeathMessage().contains("pricked to death") || event.getDeathMessage().contains("walked into a cactus")) {
				event.setDeathMessage(prefix + "hugged a cactus, spoiler alert, it wasn't a good idea");
				
				} else if (event.getDeathMessage().contains("falling anvil")) {
				event.setDeathMessage(prefix + "broke his/her head");
				
				} else if (event.getDeathMessage().contains("starved")) {
				event.setDeathMessage(prefix + "tried to reproduice a MRBeast video");
				
				} else if (event.getDeathMessage().contains("suffocated in")) {
				event.setDeathMessage(prefix + "couldn't breathe");
				
				} else if (event.getDeathMessage().contains("fell out of the world")) {
				event.setDeathMessage(prefix + "fell below the world (somehow)");
				} 
			} else {
				configstats.set(player.getKiller().getUniqueId() + ".kills", (Integer)configstats.get(player.getKiller().getUniqueId() + ".kills")+1);
				KillCount.addKill(player.getKiller());
				event.setDeathMessage(prefix+"was killed by "+player.getKiller().getName() +"["+KillCount.getKill(player.getKiller())+"]");
			}
			player.setBedSpawnLocation(new Location(Bukkit.getWorld("UHC"), 0, 100, 0));
			Fill.remove(player);
			if(Fill.get().size() == 1) {
				Bukkit.broadcastMessage(ChatColor.GOLD+Fill.get().get(0).getDisplayName()+" has won this game! Congrats!");
				Settings.setWinner(Fill.get().get(0));
				configstats.set(player.getKiller().getUniqueId() + ".wins", (Integer)configstats.get(player.getKiller().getUniqueId() + ".wins")+1);
				Bukkit.getScheduler().cancelAllTasks();
				Bukkit.getOnlinePlayers().forEach(p ->{Scoreboards.PostGame(p);});
				
				
			}
        	try {
        		configstats.save(stats);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

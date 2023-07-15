package fr.skytryx.ultihc.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.skytryx.ultihc.utils.Settings;
import fr.skytryx.ultihc.utils.Chronometer;
import fr.skytryx.ultihc.utils.Fill;
import fr.skytryx.ultihc.utils.Scoreboards;
import net.md_5.bungee.api.ChatColor;

public class OnDeath implements Listener {

	
	@EventHandler
	public void Death(PlayerDeathEvent event) {
		if(Chronometer.get() != -1) {
			Player player = event.getEntity();
			if(player.getKiller() == null) {
				event.setDeathMessage(ChatColor.RED+event.getDeathMessage());
			} else {
				event.setDeathMessage(ChatColor.RED+player.getDisplayName()+" was killed by "+player.getKiller().getDisplayName());
			}
			player.teleport(new Location(Bukkit.getWorld("UHC"), 0, 100, 0));
			Fill.remove(player);
			if(Fill.get().size() == 1) {
				Bukkit.broadcastMessage(ChatColor.GOLD+Fill.get().get(0).getDisplayName()+" has won this game! Congrats!");
				Settings.setWinner(Fill.get().get(0));
				Bukkit.getScheduler().cancelAllTasks();
				Bukkit.getOnlinePlayers().forEach(p ->{Scoreboards.PostGame(p);});
				
				
			}
		}
	}
}

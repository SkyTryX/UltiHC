package fr.skytryx.ultihc.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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
		Fill.add(player);
		player.teleport(new Location(Bukkit.getWorld("world"), 0, 51, 0));
		if(player.isOp() && Timer.get() == -1) {
			GiveInv.GiveSetupInv(player);
		} else if(player.isOp()) {
			GiveInv.GiveStaffInv(player);
		}
	}
}

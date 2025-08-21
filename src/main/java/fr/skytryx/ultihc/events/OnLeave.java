package fr.skytryx.ultihc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.skytryx.ultihc.utils.Fill;

public class OnLeave implements Listener {

	@EventHandler
	public void OnPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		Fill.remove(player);
	}
}

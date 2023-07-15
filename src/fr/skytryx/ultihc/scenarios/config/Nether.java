package fr.skytryx.ultihc.scenarios.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

import fr.skytryx.ultihc.utils.Settings;
import net.md_5.bungee.api.ChatColor;

public class Nether implements Listener {

	@EventHandler
	public void onPortal(PlayerPortalEvent event) {
		Player player = event.getPlayer();
		if(Settings.get("Nether").equals("false")) {
			player.sendMessage(ChatColor.RED+"Nether is disabled!");
			event.setCancelled(true);
		} else {
            event.useTravelAgent(true);
            event.getPortalTravelAgent().setCanCreatePortal(true);
            Location location;
            if (player.getWorld() == Bukkit.getWorld("UHC")) {
                 location = new Location(Bukkit.getWorld("UHC_nether"), event.getFrom().getBlockX() / 8, event.getFrom().getBlockY(), event.getFrom().getBlockZ() / 8);
            } else {
                location = new Location(Bukkit.getWorld("UHC"), event.getFrom().getBlockX() * 8, event.getFrom().getBlockY(), event.getFrom().getBlockZ() * 8);
            }
            event.setTo(event.getPortalTravelAgent().findOrCreate(location));
		}
	}
}

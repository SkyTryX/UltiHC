package fr.skytryx.ultihc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import fr.skytryx.ultihc.utils.Chronometer;
import fr.skytryx.ultihc.utils.Settings;
import net.md_5.bungee.api.ChatColor;

public class OnDamage implements Listener {
	
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
        	if(Chronometer.get() < Integer.parseInt(Settings.get("PvP"))*60) {
        		if(Chronometer.get() != -1) event.getDamager().sendMessage(ChatColor.RED+"PvP is not enabled!");
        		event.setCancelled(true);
        	}
        }
    }
}
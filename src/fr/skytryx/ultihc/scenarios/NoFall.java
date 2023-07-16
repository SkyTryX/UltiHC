package fr.skytryx.ultihc.scenarios;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.skytryx.ultihc.utils.Settings;

public class NoFall implements Listener {

	   @EventHandler
	    public void onHit(EntityDamageEvent event) {
		   if(!(Settings.scenariolist.contains("NoFall") || event.getEntity() instanceof Player)) return;
		   if(event.getCause() == DamageCause.FALL) {
			   event.setCancelled(true);
		   }
	    }
}

package fr.skytryx.ultihc.scenarios;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.skytryx.ultihc.utils.Chronometer;
import fr.skytryx.ultihc.utils.Settings;

public class NoClean implements Listener {
	
	public static Map<Player, Integer> NoCleanList = new HashMap<Player, Integer>();

	@EventHandler
	public void Death(PlayerDeathEvent event) {
		if(!(Settings.scenariolist.contains("NoClean") || event.getEntity().getKiller() instanceof Player) || Chronometer.get() == -1) return;
		NoCleanList.put(event.getEntity().getKiller(), Chronometer.get());
	}
	   @EventHandler
	    public void onHit(EntityDamageEvent event) {
		   if(!(Settings.scenariolist.contains("NoClean") || event.getEntity() instanceof Player) || Chronometer.get() == -1) return;
		   //Work in progress
	    }
}

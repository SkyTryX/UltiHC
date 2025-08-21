package fr.skytryx.ultihc.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffectType;

import fr.skytryx.ultihc.utils.Settings;

public class AbsorptionLess implements Listener {
	
	@EventHandler
    private void onDrink(final PlayerItemConsumeEvent event) {
        if (event.getItem().getType() == Material.GOLDEN_APPLE || !Settings.scenariolist.contains("AbsorptionLess")) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("UltiHC"), () ->{
            	event.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
            });
        }
    }
}


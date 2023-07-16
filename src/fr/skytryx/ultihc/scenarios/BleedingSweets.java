package fr.skytryx.ultihc.scenarios;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import fr.skytryx.ultihc.utils.Chronometer;
import fr.skytryx.ultihc.utils.Settings;

public class BleedingSweets implements Listener {

	@EventHandler
	public void SweetsDrop(PlayerDeathEvent event) {
		if(!Settings.scenariolist.contains("BleedingSweets") || Chronometer.get() == -1);
		event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.GOLD_INGOT, 16));
		event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.IRON_INGOT, 32));
		event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.DIAMOND, 4));
	}
}

package fr.skytryx.ultihc.scenarios;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import fr.skytryx.ultihc.utils.Settings;

public class DiamondLess implements Listener {

	@EventHandler
	public void onDiamondBreak(BlockBreakEvent event) {
		if(!Settings.scenariolist.contains("DiamondLess")) return;
		if(event.getBlock().getType() != Material.DIAMOND_ORE) return;
		
		event.setCancelled(true);
        event.getBlock().setType(Material.AIR);
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.DIRT));
	}
	
}

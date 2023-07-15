package fr.skytryx.ultihc.scenarios;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import fr.skytryx.ultihc.utils.Settings;

public class Timber implements Listener {
	
	@EventHandler
	private void onBlockBreak(BlockBreakEvent event) {
		if (event.getPlayer().isSneaking() || !Settings.scenariolist.contains("Timber")) return;
		if (event.getBlock().getType() == Material.LOG || event.getBlock().getType() == Material.LOG_2) {
			List<Block> blocks = new LinkedList<>();
			for (int i = event.getBlock().getY(); i < 255; i++) {
				Location l = new Location(event.getBlock().getWorld(), event.getBlock().getX(), i+1 ,event.getBlock().getZ());
				if (l.getBlock().getType() == Material.LOG || l.getBlock().getType() == Material.LOG_2) blocks.add(l.getBlock());
				else break;
			}
			for (int i = event.getBlock().getY(); 0 < i; i--) {
				Location l = new Location(event.getBlock().getWorld(), event.getBlock().getX(), i+1 ,event.getBlock().getZ());
				if (l.getBlock().getType() == Material.LOG || l.getBlock().getType() == Material.LOG_2) blocks.add(l.getBlock());
				else break;
			}
			for (Block block : blocks) {
				block.breakNaturally(new ItemStack(Material.DIAMOND_AXE));
			}
		}
	}
}


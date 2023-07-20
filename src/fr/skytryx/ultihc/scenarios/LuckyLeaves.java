package fr.skytryx.ultihc.scenarios;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

import fr.skytryx.ultihc.utils.Settings;

public class LuckyLeaves implements Listener {

	@EventHandler
	public void LeaveBreak(LeavesDecayEvent event) {
		event.getBlock().getDrops().remove(new ItemStack(Material.APPLE));
		if(!Settings.scenariolist.contains("LuckyLeaves")) return;
		Random r = new Random();
        int n = r.nextInt(99);
        if(n == 1) {
        	event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLDEN_APPLE));
        }
        n = r.nextInt(49);
        if(n == 1) {
        	event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE));
        }
	}
}

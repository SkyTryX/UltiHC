package fr.skytryx.ultihc.scenarios;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.skytryx.ultihc.utils.Settings;

public class Scenarios implements Listener {
	
	@EventHandler
	public void ChooseScenarios(InventoryClickEvent event) {
		if(event.getClickedInventory() == null || !(event.getClickedInventory().getName().equals("§7Scenarios")) || event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
		event.setCancelled(true);
		String ClickedItem = event.getCurrentItem().getItemMeta().getDisplayName().substring(2);
		Player player = (Player) event.getWhoClicked();
			if(Settings.scenariolist.contains(ClickedItem)) {
				Settings.removescen(ClickedItem);
				player.sendMessage("§6Removed the §b"+ClickedItem+"§6 scenario.");
			} else {
				Settings.addscen(ClickedItem);
				player.sendMessage("§6Added the §b"+ClickedItem+"§6 scenario.");
		}
	}
}

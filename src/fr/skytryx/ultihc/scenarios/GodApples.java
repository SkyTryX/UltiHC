package fr.skytryx.ultihc.scenarios;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import fr.skytryx.ultihc.utils.Settings;
import net.md_5.bungee.api.ChatColor;

public class GodApples implements Listener {

	   @EventHandler
	    public void craftItem(PrepareItemCraftEvent event) {
		   if(Settings.get("God Apples").equals("true")) return;
	        Material itemType = event.getRecipe().getResult().getType();
	        @SuppressWarnings("deprecation")
			Byte itemData = event.getRecipe().getResult().getData().getData();
	        if(itemType==Material.GOLDEN_APPLE && itemData==1){
	        	event.getInventory().setResult(new ItemStack(Material.AIR));
	            for(HumanEntity he:event.getViewers()) {
	                if(he instanceof Player) {
	                    ((Player)he).sendMessage(ChatColor.RED+"God Apples are disabled!");
	                }
	            }
	        }
	   }
}

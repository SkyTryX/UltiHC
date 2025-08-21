package fr.skytryx.ultihc.scenarios.config;

import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.skytryx.ultihc.utils.Settings;
import net.md_5.bungee.api.ChatColor;

public class Bedbombs implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
    	if(Settings.get("BedBombs").equals("true")) return;
    	if(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.BED_BLOCK){
    		if(event.getPlayer().getWorld().getEnvironment() == Environment.NETHER){
    			event.getPlayer().sendMessage(ChatColor.RED+"Bedbombs are disabled!!");
    			event.setCancelled(true);
    		}	
    	}
    }
}


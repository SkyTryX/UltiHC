package fr.skytryx.ultihc.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.skytryx.ultihc.utils.Fill;

public class AutoAssign implements Listener {

	List<List<Player>> assign = new ArrayList<>();
	boolean assign_isactivated = false;
	
	public void Assignement() {
		assign.clear();
		Bukkit.broadcastMessage("§c[AutoAssign] §6AutoAssign §bwill start assigning in 30 seconds");
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("UltiHC"), ()-> {
			Bukkit.broadcastMessage("§c[AutoAssign] §6AutoAssign §bwill start assigning in 15 seconds");
		}, 300L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("UltiHC"), ()-> {
			Bukkit.broadcastMessage("§c[AutoAssign] §6AutoAssign §bwill start assigning in 5 seconds");
		}, 500L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("UltiHC"), ()-> {
			Bukkit.broadcastMessage("§c[AutoAssign] §6AutoAssign §bwill start assigning in 3 seconds");
		}, 540L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("UltiHC"), ()-> {
			Bukkit.broadcastMessage("§c[AutoAssign] §6AutoAssign §bwill start assigning in 2 seconds");
		}, 560L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("UltiHC"), ()-> {
			Bukkit.broadcastMessage("§c[AutoAssign] §6AutoAssign §bwill start assigning in 1 second");
		}, 580L);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("UltiHC"), ()-> {
			Bukkit.broadcastMessage("§c[AutoAssign] §6AutoAssign §bis starting now!");
			for(int i = 0; Fill.get().size() > i+1; i+= 2) {
				Bukkit.broadcastMessage(Fill.get().toString());
				assign.add(Arrays.asList(Fill.get().get(i), Fill.get().get(i+1)));
				Bukkit.broadcastMessage("§c[AutoAssign] §6"+Fill.get().get(i).getDisplayName()+" §bvs §6"+Fill.get().get(i+1).getDisplayName());
			}
		}, 600L);
	}

	@EventHandler
	public void AssignActivation(PlayerInteractEvent event) {
		if(event.getItem() == null) return;
		if(event.getItem().getType() != Material.BLAZE_ROD ||
		   event.getAction() != Action.RIGHT_CLICK_AIR ||
		   assign_isactivated) return;
		assign_isactivated = true;
		Bukkit.broadcastMessage("§c[AutoAssign] §6AutoAssign §bhas been enabled");
		Assignement();
	}
	
	public static boolean cancelled;
	
	@EventHandler
	public void CheckAssign(EntityDamageByEntityEvent event) {
		cancelled = true;
		if(!assign_isactivated) return;
		if(event.getDamager().getType() != EntityType.PLAYER || event.getEntity().getType() != EntityType.PLAYER) return;
		Player damager = (Player) event.getDamager();
		Player damaged = (Player) event.getEntity();
		assign.forEach(list_assign ->{
			if(list_assign.contains(damager) && list_assign.contains(damaged)) {
				cancelled = false;
				return;
			}
		});
		if(cancelled) {
			event.setCancelled(true);
			event.getDamager().sendMessage("§c[AutoAssign] §bYou are not §6assigned §bto this player!");
		}
	} 
	List<Player> removed_list = new ArrayList<>();
	@EventHandler
	public void DeathAssign(PlayerDeathEvent event) {
		if(!assign_isactivated) return;
		removed_list = new ArrayList<>();
		Player damager = event.getEntity().getKiller();
		Player damaged = event.getEntity();
		assign.forEach(list_assign ->{
			if(list_assign.contains(damager) && list_assign.contains(damaged)) {
				removed_list = list_assign;
			}
		});
		Bukkit.broadcastMessage(assign.toString());
		if(assign.contains(removed_list)) assign.remove(removed_list);
		Bukkit.broadcastMessage(assign.toString());
		if(assign.size() == 0 && Fill.get().size() != 1) {
			Assignement();
		} else {
			assign_isactivated = false;
		}
	}
}

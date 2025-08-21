package fr.skytryx.ultihc.scenarios;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import fr.skytryx.ultihc.utils.Settings;

public class CutClean implements Listener {
		
	    @EventHandler
	    public void onBlockBreak(BlockBreakEvent event){
	    	if(!(Settings.scenariolist.contains("CutClean"))) return;
	        if (event.isCancelled()) return;
	        Block b = event.getBlock();
	        World w = b.getWorld();
	        Location l = b.getLocation();
	        ItemStack i = event.getPlayer().getItemInHand();

	        if (b.getType() == Material.IRON_ORE && (i.getType() == Material.STONE_PICKAXE || i.getType() == Material.IRON_PICKAXE || i.getType() == Material.DIAMOND_PICKAXE ||i.getType() == Material.GOLD_PICKAXE )) {
	            event.setCancelled(true);
	            b.setType(Material.AIR);
	            w.dropItemNaturally(l, new ItemStack(Material.IRON_INGOT));
	            ExperienceOrb orb = w.spawn(l, ExperienceOrb.class);
	            orb.setExperience(1);
	        } else if (b.getType() == Material.GOLD_ORE && (i.getType() == Material.STONE_PICKAXE || i.getType() == Material.IRON_PICKAXE || i.getType() == Material.DIAMOND_PICKAXE)) {
	            event.setCancelled(true);
	            b.setType(Material.AIR);
	            w.dropItemNaturally(l, new ItemStack(Material.GOLD_INGOT));
	            ExperienceOrb orb = w.spawn(l, ExperienceOrb.class);
	            orb.setExperience(1);
	        }

	    }

	    @EventHandler
	    public void onEntityDeath(EntityDeathEvent event) {
	    	if(!(Settings.scenariolist.contains("CutClean"))) return;
	        if (event.getEntity() instanceof Player) return;
	        if (event.getEntity() instanceof Cow) {
	            event.getDrops().clear();
	            event.getDrops().add(new ItemStack(Material.COOKED_BEEF, 3));
	            event.getDrops().add(new ItemStack(Material.LEATHER, 1));
	        } else if (event.getEntity() instanceof Pig) {
	            event.getDrops().clear();
	            event.getDrops().add(new ItemStack(Material.GRILLED_PORK, 3));
	        } else if (event.getEntity() instanceof Chicken) {
	            event.getDrops().clear();
	            event.getDrops().add(new ItemStack(Material.COOKED_CHICKEN, 3));
	            event.getDrops().add(new ItemStack(Material.FEATHER, 2));
	        } else if (event.getEntity() instanceof Horse) {
	            event.getDrops().clear();
	            event.getDrops().add(new ItemStack(Material.LEATHER, 2));
	        } else if (event.getEntity() instanceof PigZombie) {
	            event.getDrops().clear();
	            event.getDrops().add(new ItemStack(Material.GOLD_NUGGET, 1));
	            event.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, 1));
	        } else if (event.getEntity() instanceof Spider || event.getEntity() instanceof CaveSpider) {
	            event.getDrops().clear();
	            event.getDrops().add(new ItemStack(Material.STRING, 2));
	        } else if (event.getEntity() instanceof Zombie) {
	            event.getDrops().clear();
	            event.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, 2));
	        } else if (event.getEntity() instanceof Skeleton) {
	            event.getDrops().clear();
	            event.getDrops().add(new ItemStack(Material.ARROW, 2));
	            event.getDrops().add(new ItemStack(Material.BONE, 1));
	        } else if (event.getEntity() instanceof Creeper) {
	            event.getDrops().clear();
	            event.getDrops().add(new ItemStack(Material.SULPHUR, 2));
	    }
    }
}

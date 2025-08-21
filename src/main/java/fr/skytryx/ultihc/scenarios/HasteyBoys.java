package fr.skytryx.ultihc.scenarios;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.skytryx.ultihc.utils.Settings;

public class HasteyBoys implements Listener {

    public List<Material> tools = Arrays.asList(Material.WOOD_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE,
    Material.WOOD_SPADE, Material.STONE_SPADE, Material.IRON_SPADE, Material.GOLD_SPADE, Material.DIAMOND_SPADE,
    Material.WOOD_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE);
    
    @EventHandler
    public void onCraftPrepare(PrepareItemCraftEvent event) {
        if (!tools.contains(event.getRecipe().getResult().getType()) || !Settings.scenariolist.contains("HasteyBoys")) return;

        ItemStack result = event.getRecipe().getResult();
        ItemMeta meta = result.getItemMeta();
        meta.addEnchant(Enchantment.DIG_SPEED, 3, false);
        meta.addEnchant(Enchantment.DURABILITY, 3, false);
        event.getInventory().getResult().setItemMeta(meta);
    }

}

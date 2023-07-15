package fr.skytryx.ultihc.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveInv {

	public static void GiveSetupInv(Player player) {
		player.getInventory().clear();
		ItemStack book = new ItemStack(Material.BOOK);
		ItemMeta bookm = book.getItemMeta();
		bookm.setDisplayName("§6Configurations");
		book.setItemMeta(bookm);
		player.getInventory().setItem(0, book);
	}

	public static void GiveStaffInv(Player player) {
		player.getInventory().clear();
		ItemStack middletp = new ItemStack(Material.BEDROCK);
		ItemMeta middletpm = middletp.getItemMeta();
		middletpm.setDisplayName("§600 Teleport");
		middletp.setItemMeta(middletpm);
		player.getInventory().setItem(0, middletp);
	}
}
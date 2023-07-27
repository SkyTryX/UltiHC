package fr.skytryx.ultihc.commands;

import java.io.File;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandStats implements CommandExecutor {

	String kd_ratio;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] strings) {
		if(!(sender instanceof Player)) return false;
		Player player = (Player) sender;
        final File stats = new File(Bukkit.getServer().getPluginManager().getPlugin("UltiHC").getDataFolder(), "stats.yml");
        final YamlConfiguration configstats = YamlConfiguration.loadConfiguration(stats);
		Inventory statsinv = Bukkit.createInventory(null, 9, "§7Stats");
		ItemStack statsitem = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta statsmeta = statsitem.getItemMeta();
		if((Integer)configstats.get(player.getUniqueId() + ".death") == 0) {
			if((Integer)configstats.get(player.getUniqueId() + ".kills") == 0) {
				kd_ratio = "0";
			} else {
				kd_ratio = "Infinite";
			}
		} else {
			kd_ratio = String.valueOf((Integer)configstats.get(player.getUniqueId() + ".kills")/(Integer)configstats.get(player.getUniqueId() + ".death"));
		}
		statsmeta.setDisplayName("§6§lUHC");
		statsmeta.setLore(Arrays.asList(
				"§bRanking: §6?",
				" ",
				"§bELO: §6"+configstats.get(player.getUniqueId() + ".elo"),
				"§bWins: §6"+configstats.get(player.getUniqueId() + ".wins"),
				" ",
				"§bKills: §6"+configstats.get(player.getUniqueId() + ".kills"),
				"§bDeath: §6"+configstats.get(player.getUniqueId() + ".death"),
				"§bK/D Ratio: §6"+kd_ratio
				));
		statsitem.setItemMeta(statsmeta);
		statsinv.setItem(4, statsitem);
		for(int i = 0; statsinv.getSize() > i; i++) {
			if(statsinv.getItem(i) == null) {
				ItemStack stained = new ItemStack(Material.STAINED_GLASS_PANE);
				ItemMeta stainedmeta = stained.getItemMeta();
				stainedmeta.setDisplayName(" ");
				stained.setItemMeta(stainedmeta);
				statsinv.setItem(i, stained);
			}
		}
		player.openInventory(statsinv);
		return true;
	}

}

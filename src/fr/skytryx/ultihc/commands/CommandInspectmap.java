package fr.skytryx.ultihc.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandInspectmap implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println("[ERROR] You are not allowed to use this command in the console");
			return false;
		}
		Player player = (Player) sender;
		if(player.getWorld().getName().contains("UHC")) {
			player.sendMessage("§cERROR: You are already at this world");
			return false;
		}
		if(Bukkit.getWorld("UHC") == null) return false;
		player.setAllowFlight(true);
		player.setFlying(true);
		player.teleport(new Location(Bukkit.getWorld("UHC"), 0, 100, 0));
		return false;
	}

}
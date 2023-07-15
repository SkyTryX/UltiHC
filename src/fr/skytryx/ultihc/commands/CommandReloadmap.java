package fr.skytryx.ultihc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.skytryx.ultihc.utils.WorldCreation;

public class CommandReloadmap implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		WorldCreation.CreateWorld();
		return false;
	}

}

package fr.skytryx.ultihc;

import org.bukkit.plugin.java.JavaPlugin;

import fr.skytryx.ultihc.commands.CommandInspectmap;
import fr.skytryx.ultihc.commands.CommandReloadmap;
import fr.skytryx.ultihc.events.OnDeath;
import fr.skytryx.ultihc.events.OnJoin;
import fr.skytryx.ultihc.events.OnLeave;
import fr.skytryx.ultihc.events.OnDamage;
import fr.skytryx.ultihc.events.ToolsClick;
import fr.skytryx.ultihc.scenarios.CutClean;
import fr.skytryx.ultihc.scenarios.HasteyBoys;
import fr.skytryx.ultihc.scenarios.NoClean;
import fr.skytryx.ultihc.scenarios.Timber;
import fr.skytryx.ultihc.scenarios.config.Bedbombs;
import fr.skytryx.ultihc.scenarios.config.GodApples;
import fr.skytryx.ultihc.scenarios.config.Nether;
import fr.skytryx.ultihc.scenarios.config.Scenarios;
import fr.skytryx.ultihc.utils.Settings;
import fr.skytryx.ultihc.utils.WorldCreation;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getCommand("inspectmap").setExecutor(new CommandInspectmap());
		getCommand("reloadmap").setExecutor(new CommandReloadmap());
		
		
		getServer().getPluginManager().registerEvents(new ToolsClick(this), this);
		
		getServer().getPluginManager().registerEvents(new OnJoin(), this);
		getServer().getPluginManager().registerEvents(new OnLeave(), this);
		getServer().getPluginManager().registerEvents(new OnDeath(), this);
		getServer().getPluginManager().registerEvents(new OnDamage(), this);
		
		getServer().getPluginManager().registerEvents(new Scenarios(), this);
		getServer().getPluginManager().registerEvents(new Nether(), this);
		getServer().getPluginManager().registerEvents(new Bedbombs(), this);
		getServer().getPluginManager().registerEvents(new GodApples(), this);
		getServer().getPluginManager().registerEvents(new CutClean(), this);
		getServer().getPluginManager().registerEvents(new NoClean(), this);
		getServer().getPluginManager().registerEvents(new Timber(), this);
		getServer().getPluginManager().registerEvents(new HasteyBoys(), this);
		
		System.out.println("[UltiHC] Plugin has been enabled");
		WorldCreation.CreateWorld();
		Settings.reset();
	}
	
	@Override
	public void onDisable() {
		System.out.println("[UltiHC] Plugin has been enabled");
	}
}

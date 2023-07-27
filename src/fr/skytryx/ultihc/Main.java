package fr.skytryx.ultihc;

import java.util.Arrays;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import fr.skytryx.ultihc.commands.CommandInspectmap;
import fr.skytryx.ultihc.commands.CommandReloadmap;
import fr.skytryx.ultihc.commands.CommandStats;
import fr.skytryx.ultihc.events.OnDeath;
import fr.skytryx.ultihc.events.OnJoin;
import fr.skytryx.ultihc.events.OnLeave;
import fr.skytryx.ultihc.events.OnDamage;
import fr.skytryx.ultihc.scenarios.AbsorptionLess;
import fr.skytryx.ultihc.scenarios.BleedingSweets;
import fr.skytryx.ultihc.scenarios.CutClean;
import fr.skytryx.ultihc.scenarios.DiamondLess;
import fr.skytryx.ultihc.scenarios.HasteyBoys;
import fr.skytryx.ultihc.scenarios.LuckyLeaves;
import fr.skytryx.ultihc.scenarios.NoClean;
import fr.skytryx.ultihc.scenarios.NoFall;
import fr.skytryx.ultihc.scenarios.Timber;
import fr.skytryx.ultihc.scenarios.config.Bedbombs;
import fr.skytryx.ultihc.scenarios.config.GodApples;
import fr.skytryx.ultihc.scenarios.config.Nether;
import fr.skytryx.ultihc.scenarios.config.Scenarios;
import fr.skytryx.ultihc.tools.AutoAssign;
import fr.skytryx.ultihc.tools.ToolsClick;
import fr.skytryx.ultihc.utils.Settings;
import fr.skytryx.ultihc.utils.WorldCreation;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getCommand("inspectmap").setExecutor(new CommandInspectmap());
		getCommand("reloadmap").setExecutor(new CommandReloadmap());
		getCommand("stats").setExecutor(new CommandStats());
		
		
		getServer().getPluginManager().registerEvents(new ToolsClick(this), this);
		getServer().getPluginManager().registerEvents(new AutoAssign(), this);
		
		getServer().getPluginManager().registerEvents(new OnJoin(), this);
		getServer().getPluginManager().registerEvents(new OnLeave(), this);
		getServer().getPluginManager().registerEvents(new OnDeath(), this);
		getServer().getPluginManager().registerEvents(new OnDamage(), this);
		
		Arrays.asList(new Scenarios(), new Nether(), new Bedbombs(), new GodApples()
				).forEach(c ->{
					getServer().getPluginManager().registerEvents(c, this);
				});
		
		Arrays.asList(new CutClean(), new NoClean(), new Timber(),
				new HasteyBoys(), new NoFall(), new AbsorptionLess(), new BleedingSweets(),
				new LuckyLeaves(), new DiamondLess()).forEach(c ->{
					getServer().getPluginManager().registerEvents((Listener) c, this);
				});
		
		System.out.println("[UltiHC] Plugin has been enabled");
		WorldCreation.CreateWorld();
		Settings.reset();
	}
	
	@Override
	public void onDisable() {
		System.out.println("[UltiHC] Plugin has been enabled");
	}
}

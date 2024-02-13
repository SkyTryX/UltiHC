package fr.skytryx.ultihc.tools;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.skytryx.ultihc.Main;
import fr.skytryx.ultihc.utils.Settings;
import fr.skytryx.ultihc.utils.Timer;
import fr.skytryx.ultihc.utils.GiveInv;
import fr.skytryx.ultihc.utils.Scoreboards;


public class ToolsClick implements Listener{
	
	
    private Main main;
    public String input = null;
 
    public ToolsClick(Main main) {
        this.main = main;
    }
	
	private ItemStack ItemCreator(String displayname, Material mat, String lore) {
		ItemStack item = new ItemStack(mat);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(displayname);
		itemm.setLore(Arrays.asList("§b"+lore));
		item.setItemMeta(itemm);
		return item;
	}
	@EventHandler
	public void OnClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		
		if(event.getItem() == null) return;
		
		if(item.getItemMeta().getDisplayName() == "§6Configurations") {
			event.setCancelled(true);
			Inventory configinv = Bukkit.createInventory(null, 18, "§7Configurations");
			Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
	            @Override
	            public void run() {
	            	if(configinv.getViewers().size() == 0)  Bukkit.getScheduler().cancelAllTasks();
					configinv.clear();
					configinv.addItem(ItemCreator("§6Team Size", Material.ITEM_FRAME, "To"+Settings.get("Team Size")));
					configinv.addItem(ItemCreator("§6Border", Material.BEDROCK, Settings.get("Border")+"x"));
					configinv.addItem(ItemCreator("§6FirstShrink", Material.WOOD_DOOR, Settings.get("FirstShrink")+"m"));
					configinv.addItem(ItemCreator("§6ShrinkTime", Material.WATCH, Settings.get("ShrinkTime")+"m"));
					configinv.addItem(ItemCreator("§6Monster Spawning", Material.SKULL_ITEM, Settings.get("Monster Spawning")));
					configinv.addItem(ItemCreator("§6Nether", Material.NETHERRACK, Settings.get("Nether")));
					configinv.addItem(ItemCreator("§6PvP", Material.DIAMOND_SWORD, Settings.get("PvP")+"m"));
					configinv.addItem(ItemCreator("§6BedBombs", Material.BED, Settings.get("BedBombs")));
					configinv.addItem(ItemCreator("§6God Apples", Material.GOLDEN_APPLE, Settings.get("God Apples")));
					configinv.addItem(ItemCreator("§6FinalHeal", Material.GOLDEN_CARROT, Settings.get("FinalHeal")+"m"));
					configinv.setItem(configinv.getSize()-2, ItemCreator("§6Scenarios", Material.BOOKSHELF, Settings.get("Scenarios").replace("[", "").replace("]", "")));
					configinv.setItem(configinv.getSize()-1, ItemCreator("§6Start", Material.EMERALD_BLOCK, "Click here to start the uhc!"));
	            }
	        }, 1L, 1L);
			player.openInventory(configinv);
		}
	} 
	
	@EventHandler
	public void OnInvClick(InventoryClickEvent event) {
		if(!event.getInventory().getName().equals("§7Configurations") || event.getCurrentItem().getType() == null) return;
		event.setCancelled(true);
		String ClickedItem = event.getCurrentItem().getItemMeta().getDisplayName().substring(2);
		Player player = (Player) event.getWhoClicked();
		if(Arrays.asList("Nether", "Monster Spawning", "BedBombs", "God Apples").contains(ClickedItem)) {
			if(Settings.get(ClickedItem).equals("true")){
				Settings.set(ClickedItem, "false");
			} else Settings.set(ClickedItem, "true");

		} else if (Arrays.asList("PvP", "FinalHeal", "Team Size", "Border", "FirstShrink", "ShrinkTime").contains(ClickedItem)) {
			input = ClickedItem;
			player.closeInventory();
			player.sendMessage("§4[Input] §bSend in chat the "+ClickedItem+" as an integer (10 for example)");

		} else if (ClickedItem.equals("Scenarios")) {
			Inventory scenarioinv = Bukkit.createInventory(null, 27, "§7Scenarios");
			scenarioinv.addItem(ItemCreator("§6CutClean", Material.LAVA_BUCKET, "Cooks ores instantly."));
			scenarioinv.addItem(ItemCreator("§6NoClean", Material.DIAMOND_SWORD, "Gives you 30 seconds of invisibility after a kill §cWORK IN PROGRESS"));
			scenarioinv.addItem(ItemCreator("§6Timber", Material.LOG, "Breaking one log breaks the whole tree"));
			scenarioinv.addItem(ItemCreator("§6HasteyBoys", Material.GOLD_PICKAXE, "Gives Efficiency 3 and Unbreaking 3 to tools"));
			scenarioinv.addItem(ItemCreator("§6NoFall", Material.GOLD_BOOTS, "Players are immune to fall damage"));
			scenarioinv.addItem(ItemCreator("§6AbsorptionLess", Material.GOLDEN_APPLE, "GoldenApples does not grant absorption anymore"));
			scenarioinv.addItem(ItemCreator("§6BleedingSweets", Material.SUGAR, "Killing players gives ores"));
			scenarioinv.addItem(ItemCreator("§6LuckyLeaves", Material.LEAVES, "Golden apples sometimes drops when a leave breaks"));
			scenarioinv.addItem(ItemCreator("§6DiamondLess", Material.DIAMOND, "Diamond doesn't drop when breaking a diamond ore"));
			player.closeInventory();
			player.openInventory(scenarioinv);	
		} else if (ClickedItem.equals("Start")) {
			input = "Timer";
			player.closeInventory();
			player.sendMessage("§4[Input] §bSend in chat the timer in seconds as an integer (10 for example)");
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		if(input == null || !(event.getPlayer().isOp())) return;
		event.setCancelled(true);
		try {
			Integer.parseInt(event.getMessage());
		} catch(java.lang.NumberFormatException e){
			event.getPlayer().sendMessage("§c[ERROR] It needs to be an integer...");
			input = null;
			return;
		}
		if(Arrays.asList("PvP", "FinalHeal", "Team Size", "Border", "FirstShrink", "ShrinkTime").contains(input)){
			Settings.set(input, event.getMessage());
			event.getPlayer().sendMessage("§bSet "+input+" to §6"+event.getMessage());
		} else if(input.equals("Timer")) {
			Timer.set(Integer.parseInt(event.getMessage()));
			event.getPlayer().getInventory().clear();
			GiveInv.GiveStaffInv(event.getPlayer());
			Timer.start();
			Bukkit.getOnlinePlayers().forEach(p ->{
				Scoreboards.PreGame(p);
			});
			event.getPlayer().sendMessage("§6The UHC has been set up!");
		}
		input = null;
	}
	@EventHandler
	public void StatsClick(InventoryClickEvent event) {
		if(event.getClickedInventory() == null) return;
		if(!event.getInventory().getName().equals("§7Stats") || event.getCurrentItem().getType() == Material.AIR) return;
		event.setCancelled(true);
	}
}

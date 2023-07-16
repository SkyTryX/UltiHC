package fr.skytryx.ultihc.events;

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
		if(event.getItem() == null || event.getItem().getItemMeta() == null) return;
		
		
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		
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
					configinv.addItem(ItemCreator("§6Monster Spawning", Material.SKULL_ITEM, Settings.get("Monster Spawning")));
					configinv.addItem(ItemCreator("§6Nether", Material.NETHERRACK, Settings.get("Nether")));
					configinv.addItem(ItemCreator("§6PvP", Material.DIAMOND_SWORD, Settings.get("PvP")+"m"));
					configinv.addItem(ItemCreator("§6BedBombs", Material.BED, Settings.get("BedBombs")));
					configinv.addItem(ItemCreator("§6God Apples", Material.GOLDEN_APPLE, Settings.get("God Apples")));
					configinv.addItem(ItemCreator("§6FinalHeal", Material.GOLDEN_CARROT, Settings.get("FinalHeal")+"m"));
					configinv.setItem(configinv.getSize()-2, ItemCreator("§6Scenarios", Material.BOOKSHELF, Settings.get("Scenarios")));
					configinv.setItem(configinv.getSize()-1, ItemCreator("§6Start", Material.EMERALD_BLOCK, "Click here to start the uhc!"));
	            }
	        }, 1L, 1L);
			player.openInventory(configinv);
		}
	} 
	
	@EventHandler
	public void OnInvClick(InventoryClickEvent event) {
		if(event.getClickedInventory() == null || !(event.getClickedInventory().getName().equals("§7Configurations")) || event.getCurrentItem() == null) return;
		event.setCancelled(true);
		String ClickedItem = event.getCurrentItem().getItemMeta().getDisplayName().substring(2);
		Player player = (Player) event.getWhoClicked();
		if(ClickedItem.equals("Nether")) {
			if(Settings.get("Nether").equals("true")){
				Settings.set("Nether", "false");
			} else Settings.set("Nether", "true");
		} else if (ClickedItem.equals("Monster Spawning")) {
			if(Settings.get("Monster Spawning").equals("true")){
				Settings.set("Monster Spawning", "false");
			} else Settings.set("Monster Spawning", "true");
		} else if (ClickedItem.equals("BedBombs")) {
			if(Settings.get("BedBombs").equals("true")){
				Settings.set("BedBombs", "false");
			} else Settings.set("BedBombs", "true");
		} else if (ClickedItem.equals("God Apples")) {
			if(Settings.get("God Apples").equals("true")){
				Settings.set("God Apples", "false");
			} else Settings.set("God Apples", "true");
		} else if (ClickedItem.equals("PvP")) {
			input = "PvP";
			player.closeInventory();
			player.sendMessage("§4[Input] §bSend in chat the timestamp as an integer (10 for example)");
		} else if (ClickedItem.equals("FinalHeal")) {
			input = "FinalHeal";
			player.closeInventory();
			player.sendMessage("§4[Input] §bSend in chat the timestamp as an integer (10 for example)");
		} else if (ClickedItem.equals("Team Size")) {
			input = "Team Size";
			player.closeInventory();
			player.sendMessage("§4[Input] §bSend in chat the team size as an integer (10 for example)");
		} else if (ClickedItem.equals("Border")) {
			input = "Border";
			player.closeInventory();
			player.sendMessage("§4[Input] §bSend in chat the border size as an integer (10 for example)");
		} else if (ClickedItem.equals("Scenarios")) {
			Inventory scenarioinv = Bukkit.createInventory(null, 27, "§7Scenarios");
			
			scenarioinv.addItem(ItemCreator("§6CutClean", Material.LAVA_BUCKET, "Cooks ores instantly."));
			scenarioinv.addItem(ItemCreator("§6NoClean", Material.DIAMOND_SWORD, "Gives you 30 seconds of invisibility after a kill §cWORK IN PROGRESS"));
			scenarioinv.addItem(ItemCreator("§6Timber", Material.LOG, "Breaking one log breaks the whole tree"));
			scenarioinv.addItem(ItemCreator("§6HasteyBoys", Material.GOLD_PICKAXE, "Gives Efficiency 3 and Unbreaking 3 to tools"));
			scenarioinv.addItem(ItemCreator("§6NoFall", Material.GOLD_BOOTS, "Players are immune to fall damage"));
			player.closeInventory();
			player.openInventory(scenarioinv);	
		} else if (ClickedItem.equals("Start")) {
			player.closeInventory();
			player.sendMessage("§4[Input] §bSend in chat the timer in seconds as an integer (10 for example)");
			input = "Timer";
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
		if(input == "PvP" || input == "FinalHeal" || input == "Team Size" || input == "Border") {
			Settings.set(input, event.getMessage());
			event.getPlayer().sendMessage("§bSet "+input+" to §6"+event.getMessage());
		} else if(input == "Timer") {
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
}

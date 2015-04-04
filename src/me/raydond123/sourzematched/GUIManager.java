package me.raydond123.sourzematched;

import me.raydond123.sourzematched.SourzeMatched;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIManager implements Listener {

    SourzeMatched plugin;

    public GUIManager(SourzeMatched plugin) {

        this.plugin = plugin;

    }

    Inventory gui;

    public void setupGUI() {

        gui = Bukkit.createInventory(null, 9, ChatColor.GREEN + "" + ChatColor.BOLD + "Fight Menu");

        ItemStack first = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta firstMeta = first.getItemMeta();
        firstMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "1v1");
        first.setItemMeta(firstMeta);
        gui.setItem(0, first);

        ItemStack second = new ItemStack(Material.DIAMOND_SWORD, 2);
        ItemMeta secondMeta = second.getItemMeta();
        secondMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "2v2");
        second.setItemMeta(secondMeta);
        gui.setItem(1, second);

        ItemStack third = new ItemStack(Material.DIAMOND_SWORD, 3);
        ItemMeta thirdMeta = third.getItemMeta();
        thirdMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "3v3");
        third.setItemMeta(thirdMeta);
        gui.setItem(2, third);

    }

    public void openGUI(Player player) {

        player.openInventory(gui);

    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        Inventory inventory = e.getInventory();

        if(inventory == gui) {

            e.setCancelled(true);
            
            ItemStack clicked = e.getCurrentItem();
            
            if(clicked.getType() == Material.DIAMOND_SWORD) {
                
                int amount = clicked.getAmount();
                
                if(amount == 1) {
                    
                    plugin.queueManager.addToQueue(player, 1);
                    
                } else if(amount == 2) {

                    plugin.queueManager.addToQueue(player, 2);
                    
                } else if(amount == 3) {

                    plugin.queueManager.addToQueue(player, 3);
                    
                }
                
            }

        }

    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        ItemStack held = player.getItemInHand();

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if(held.getType() == Material.COMPASS) {

                openGUI(player);

            }

        }

    }

}

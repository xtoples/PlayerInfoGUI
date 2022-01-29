package me.toples.playerinfogui.listeners;

import me.toples.playerinfogui.PlayerInfoGUI;
import me.toples.playerinfogui.menus.PlayerMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class PlayerMenuEvent implements Listener {
    private PlayerInfoGUI plugin;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        InventoryHolder holder = inv.getHolder();
        if (holder instanceof PlayerMenu) {
            PlayerMenu menu = (PlayerMenu) holder;
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                event.setCancelled(true);
                menu.onClick(player, event.getCurrentItem(), event.getSlot());
            }
        }
    }
}


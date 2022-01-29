package me.toples.playerinfogui.menus;

import me.toples.playerinfogui.PlayerInfoGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import me.toples.playerinfogui.utilities.CC;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class PlayerMenu extends Command {
    private final PlayerInfoGUI plugin;

    public PlayerMenu(PlayerInfoGUI plugin) {
        super("playerinfogui");
        this.plugin = plugin;
        this.registerCommand(this);
        this.setDescription("Opens the PlayerInfoGUI");
        this.setAliases(Arrays.asList("pigui", "playergui"));
    }

    private void registerCommand(Command command) {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            commandMap.register(plugin.getDescription().getName(), command);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }


    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cYou must be a player to use this command!"));
            return true;
        }
        Player player = (Player) Bukkit.getOnlinePlayers();
        UUID uuid = player.getUniqueId();
        ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1);
        SkullMeta skull = (SkullMeta) playerHead.getItemMeta();
        ItemMeta meta = playerHead.getItemMeta();
        Inventory gui = Bukkit.createInventory(player, 54, CC.translate("&bPlayer Info"));

        skull.setOwner(String.valueOf(plugin.getServer().getOfflinePlayer(uuid)));
        playerHead.setItemMeta(skull);
        meta.setDisplayName(String.valueOf(meta));

        ArrayList<String> lore = new ArrayList<>();
        lore.add(CC.translate("&aHealth:" + player.getHealth()));
        lore.add(CC.translate("&aFood:" + player.getFoodLevel()));
        lore.add(CC.translate("&aLevel:" + player.getLevel()));
        lore.add(CC.translate("&aExp:" + player.getExp()));
        lore.add(CC.translate("&aLocation:" + player.getLocation().getBlockX() + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ()));
        lore.add(CC.translate("&aWorld:" + player.getWorld().getName()));
        lore.add(CC.translate("&aIP:" + player.getAddress().getHostString()));
        lore.add(CC.translate("&aGamemode:" + player.getGameMode().toString()));
        lore.add(CC.translate("&aFly:" + player.getAllowFlight()));
        lore.add(CC.translate("&aUUID:" + uuid.toString()));
        meta.setLore(lore);

        if (!(gui.contains(playerHead))) {
            gui.addItem(playerHead);
        }
        return true;
    }

    public void onClick(Player player, ItemStack currentItem, int slot) {
        if (currentItem.getType() == Material.SKULL_ITEM) {
            player.sendMessage(CC.translate("&cYou cannot click on this item."));
        }
    }
}

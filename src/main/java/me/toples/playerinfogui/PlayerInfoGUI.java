package me.toples.playerinfogui;

import lombok.Getter;
import me.toples.playerinfogui.listeners.PlayerMenuEvent;
import me.toples.playerinfogui.menus.PlayerMenu;
import me.toples.playerinfogui.utilities.Description;
import me.toples.playerinfogui.utilities.CC;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerInfoGUI extends JavaPlugin {
    @Getter
    public static PlayerInfoGUI instance;
    @Getter
    private final PluginManager pluginManager = Bukkit.getPluginManager();

    public PlayerInfoGUI() {
        instance = this;
    }

    public void onEnable() {
        registerCommandEvents();
        registerConfig();
        saveDefaultConfig();

        getLogger().info(CC.translate("&7==============================="));
        getLogger().info(CC.translate("&aPlugin: &d" + Description.getName()));
        getLogger().info(CC.translate("&aAuthor: &d" + Description.getAuthor()));
        getLogger().info(CC.translate("&aVersion: &d" + Description.getVersion()));
        getLogger().info(CC.translate("&aStatus: &aEnabled"));
        getLogger().info(CC.translate("&7==============================="));

        if (!Description.getAuthor().contains("Toples")) {
            getLogger().info(CC.translate("------------------------------------------------"));
            getLogger().info(CC.translate("&cYou edited the plugin.yml, please don't do that"));
            getLogger().info(CC.translate("&cPlease check your plugin.yml and try again."));
            getLogger().info(CC.translate("            &cDisabling PlayerInfoGUI"));
            getLogger().info(CC.translate("------------------------------------------------"));
            pluginManager.disablePlugin(this);
            return;
        }
        if (!Description.getName().contains("PlayerInfoGUI")) {
            getLogger().info(CC.translate("------------------------------------------------"));
            getLogger().info(CC.translate("&cYou edited the plugin.yml, please don't do that"));
            getLogger().info(CC.translate(" &cPlease check your plugin.yml and try again."));
            getLogger().info(CC.translate("            &cDisabling PlayerInfoGUI"));
            getLogger().info(CC.translate("------------------------------------------------"));
            pluginManager.disablePlugin(this);
            return;
        }
    }

    public void onDisable() {
        instance = null;
        getLogger().info(CC.translate("&7==========================="));
        getLogger().info(CC.translate("&aPlugin: &d" + Description.getName()));
        getLogger().info(CC.translate("&aAuthor: &d" + Description.getAuthor()));
        getLogger().info(CC.translate("&aVersion: &d" + Description.getVersion()));
        getLogger().info(CC.translate("&aStatus: &cDisabled"));
        getLogger().info(CC.translate("&7==========================="));
    }
    public void registerConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    public void registerCommandEvents() {
        new PlayerMenu(this);
        pluginManager.registerEvents(new PlayerMenuEvent(),this);
    }
}

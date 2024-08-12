package org.twipnetwork.hidePlugins;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public final class HidePlugins extends JavaPlugin implements Listener {

    public static HidePlugins plugin;
    private List<String> disabledCmds;
    private String blockMsg;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        FileConfiguration config = getConfig();

        blockMsg = ChatColor.translateAlternateColorCodes('&', config.getString("blockMsg", "&cYou are not allowed to use this command."));

        disabledCmds = Arrays.asList(
                "/pl", "/bukkit:pl", "/plugins", "/bukkit:plugins", "/ver", "/bukkit:ver", "/version", "/bukkit:version",
                "/?", "/bukkit:?", "/help", "/bukkit:help", "/about", "/bukkit:about", "/icanhasbukkit", "/bukkit:icanhasbukkit"
        );

        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onDisabledCommandExecution(PlayerCommandPreprocessEvent event) {
        if (!event.getPlayer().hasPermission("hide_plugins.bypass")) {
            String message = event.getMessage();
            String[] array = message.split(" ");

            if (disabledCmds.contains(array[0].toLowerCase())) {
                event.getPlayer().sendMessage(blockMsg);
                event.setCancelled(true);
            }
        }
    }
}

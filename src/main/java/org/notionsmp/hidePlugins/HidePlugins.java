package org.notionsmp.hidePlugins;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@Getter
public final class HidePlugins extends JavaPlugin {
    @Getter
    private static HidePlugins instance;
    private PaperCommandManager commandManager;
    private MiniMessage miniMessage;
    private List<String> disabledCommands;
    private String blockMsg;

    @Override
    public void onEnable() {
        instance = this;
        this.miniMessage = MiniMessage.miniMessage();

        saveDefaultConfig();
        reloadConfigValues();

        this.commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new HidePluginsCommand());

        getServer().getPluginManager().registerEvents(new CommandListener(), this);
    }

    public void reloadConfigValues() {
        reloadConfig();
        this.blockMsg = getConfig().getString("blockMsg", "<red>You are not allowed to use this command");
        this.disabledCommands = getConfig().getStringList("disabled-commands");
    }
}
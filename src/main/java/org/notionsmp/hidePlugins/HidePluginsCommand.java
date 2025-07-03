package org.notionsmp.hidePlugins;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

@CommandAlias("hideplugins|hp")
@CommandPermission("hide_plugins.reload")
public class HidePluginsCommand extends BaseCommand {

    @Default
    @Subcommand("reload")
    public void onReload(CommandSender sender) {
        HidePlugins.getInstance().reloadConfigValues();
        Component msg = HidePlugins.getInstance().getMiniMessage()
                .deserialize("<green>HidePlugins configuration reloaded!");
        sender.sendMessage(msg);
    }
}
package org.notionsmp.hidePlugins;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.TabCompleteEvent;

public class CommandListener implements Listener {

    @EventHandler
    public void onDisabledCommandExecution(PlayerCommandPreprocessEvent event) {
        if (!event.getPlayer().hasPermission("hide_plugins.bypass")) {
            String[] args = event.getMessage().split(" ");
            if (args.length == 0) return;

            if (isDisabledCommand(args[0])) {
                Component msg = HidePlugins.getInstance().getMiniMessage()
                        .deserialize(HidePlugins.getInstance().getBlockMsg());
                event.getPlayer().sendMessage(msg);
                event.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onTabComplete(TabCompleteEvent event) {
        if (event.getSender().hasPermission("hide_plugins.bypass")) return;

        String buffer = event.getBuffer();
        if (buffer.startsWith("/")) {
            String[] parts = buffer.split(" ", 2);
            String command = parts[0];

            if (isDisabledCommand(command)) {
                event.setCancelled(true);
            }
        }
    }

    private boolean isDisabledCommand(String rawCommand) {
        String cmd = rawCommand.toLowerCase();
        if (cmd.startsWith("/")) {
            cmd = cmd.substring(1);
        }
        return HidePlugins.getInstance().getDisabledCommands().contains(cmd);
    }
}
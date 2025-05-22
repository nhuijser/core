package me.display.core.listener;

import me.display.core.Core;
import me.display.core.config.ColorHandler;
import me.display.core.config.MotdHandler;
import me.display.core.gui.MotdGui;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.entity.Player;

public class MotdChatListener implements Listener {

    private final Core plugin;
    private final Player player;
    private final MotdGui gui;
    private final boolean isAdding;
    private final int lineIndex;

    public MotdChatListener(Core plugin, Player player, MotdGui gui, boolean isAdding, int lineIndex) {
        this.plugin = plugin;
        this.player = player;
        this.gui = gui;
        this.isAdding = isAdding;
        this.lineIndex = lineIndex;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!event.getPlayer().equals(player)) return;

        event.setCancelled(true);
        String message = event.getMessage();

        MotdHandler motdHandler = plugin.getMotdHandler();
        ColorHandler colorHandler = plugin.getColorHandler();

        if (isAdding) {
            motdHandler.addLine(message);
            player.sendMessage(
                    Component.text("Added new MOTD line: ", colorHandler.getPrimaryColor())
                            .append(Component.text(message, colorHandler.getSecondaryColor()))
            );
        } else {
            motdHandler.editLine(lineIndex, message);
            player.sendMessage(
                    Component.text("Edited MOTD line #" + (lineIndex + 1) + ": ", colorHandler.getPrimaryColor())
                            .append(Component.text(message, colorHandler.getSecondaryColor()))
            );
        }

        plugin.getServer().getScheduler().runTask(plugin, () -> {
            gui.setupInventory();
            gui.open();
        });

        HandlerList.unregisterAll(this);
    }
}

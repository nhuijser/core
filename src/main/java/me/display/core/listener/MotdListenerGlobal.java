package me.display.core.listener;

import me.display.core.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.List;

public class MotdListenerGlobal implements Listener {

    private final Core plugin;

    public MotdListenerGlobal(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        List<String> lines = plugin.getMotdHandler().getMotdLines();
        if (lines.isEmpty()) {
            event.setMotd("Welcome to the server!");
        } else {
            StringBuilder motd = new StringBuilder();
            for (String line : lines) {
                motd.append(line).append("\n");
            }
            event.setMotd(motd.toString().trim());
        }
    }
}

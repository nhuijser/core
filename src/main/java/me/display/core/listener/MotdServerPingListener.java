package me.display.core.listener;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import me.display.core.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.List;

public class MotdServerPingListener implements Listener {

    private final Core plugin;

    public MotdServerPingListener(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerListPing(PaperServerListPingEvent event) {
        List<String> lines = plugin.getMotdHandler().getMotdLines();

        if (lines.isEmpty()) {
            System.out.println("MOTD lines are empty!");
            event.setMotd("Welcome to the server!");
            return;
        }

        // Build MOTD string with proper color code conversion
        StringBuilder motdBuilder = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            System.out.println("Original line " + i + ": " + line);
            // Convert & codes to § codes manually
            line = line.replaceAll("&([0-9a-fk-or])", "§$1");
            System.out.println("Converted line " + i + ": " + line);
            motdBuilder.append(line);
            if (i != lines.size() - 1) {
                motdBuilder.append("\n");
            }
        }

        System.out.println("Final MOTD being set: " + motdBuilder.toString());
        event.setMotd(motdBuilder.toString());
    }
}
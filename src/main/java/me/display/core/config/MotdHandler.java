package me.display.core.config;

import me.display.core.Core;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MotdHandler {

    private final Core plugin;
    private List<String> motdLines;

    public MotdHandler(Core plugin) {
        this.plugin = plugin;
        this.motdLines = new ArrayList<>();
    }

    public void loadConfig() {
        FileConfiguration config = plugin.getConfig();
        motdLines = config.getStringList("motd.lines");
        if (motdLines == null) {
            motdLines = new ArrayList<>();
        }
    }

    public void saveConfig() {
        plugin.getConfig().set("motd.lines", motdLines);
        plugin.saveConfig();
    }

    public List<String> getMotdLines() {
        return motdLines;
    }

    public void addLine(String line) {
        if (motdLines.size() < 2) {
            motdLines.add(line);
            saveConfig();
        } else {
            // Optionally notify or log that the limit was reached
            plugin.getLogger().warning("Cannot add more than 2 MOTD lines.");
        }
    }


    public void editLine(int index, String newLine) {
        if (index >= 0 && index < motdLines.size()) {
            motdLines.set(index, newLine);
            saveConfig();
        }
    }

    public void removeLine(int index) {
        if (index >= 0 && index < motdLines.size()) {
            motdLines.remove(index);
            saveConfig();
        }
    }
}

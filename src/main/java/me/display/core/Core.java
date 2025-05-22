package me.display.core;

import me.display.core.config.ColorHandler;
import me.display.core.config.MotdHandler;
import me.display.core.listener.MotdChatListener;
import me.display.core.listener.MotdGuiListener; // ADD THIS
import me.display.core.listener.MotdListenerGlobal;
import me.display.core.listener.MotdServerPingListener;
import me.vaperion.blade.Blade;
import me.vaperion.blade.paper.BladePaperPlatform;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core instance;
    private ColorHandler colorHandler;
    private MotdHandler motdHandler;

    public static Core getInstance() {
        return instance;
    }

    public ColorHandler getColorHandler() {
        return colorHandler;
    }

    public MotdHandler getMotdHandler() {
        return motdHandler;
    }

    @Override
    public void onEnable() {
        getLogger().info("Starting Core plugin " + getDescription().getVersion() + "...");

        instance = this;

        saveDefaultConfig();

        // Load colors from config
        String primary = getConfig().getString("PRIMARY_COLOR", "white");
        String secondary = getConfig().getString("SECONDARY_COLOR", "white");
        colorHandler = new ColorHandler(primary, secondary);

        // Initialize MOTD handler and load config
        motdHandler = new MotdHandler(this);
        motdHandler.loadConfig();

        // Use Adventure to log colored message
        TextColor primaryColor = colorHandler.getPrimaryColor();
        Component startupMessage = Component.text("Core plugin enabled!").color(primaryColor);
        getComponentLogger().info(startupMessage);

        // Initialize Blade command framework
        Blade.forPlatform(new BladePaperPlatform(this))
                .config(cfg -> {
                    cfg.setFallbackPrefix("core");
                    cfg.setDefaultPermissionMessage("No permission!");
                })
                .build()
                .registerPackage(Core.class, "me.display.core.command");

        // Register MOTD listeners for live MOTD updates and GUI handling
        getServer().getPluginManager().registerEvents(new MotdServerPingListener(this), this);
        getServer().getPluginManager().registerEvents(new MotdGuiListener(this), this);

        // (Optional) Register more listeners or commands here
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}

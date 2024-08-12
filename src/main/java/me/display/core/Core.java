package me.display.core;

import me.vaperion.blade.Blade;
import me.vaperion.blade.paper.BladePaperPlatform;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core instance;

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        getLogger().info("Starting Core plugin " + getDescription().getVersion() + "...");

        instance = this;

        // Initialize Blade command framework
        Blade.forPlatform(new BladePaperPlatform(this))
                .config(cfg -> {
                    cfg.setFallbackPrefix("core");
                    cfg.setDefaultPermissionMessage("No permission!");
                })
                .build()
                .registerPackage(Core.class, "me.display.core.command");
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}

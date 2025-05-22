package me.display.core.listener;

import me.display.core.Core;
import me.display.core.config.ColorHandler;
import me.display.core.config.MotdHandler;
import me.display.core.gui.MotdGui;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class MotdGuiListener implements Listener {

    private final Core plugin;

    public MotdGuiListener(Core plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ColorHandler colorHandler = Core.getInstance().getColorHandler();

        var primary = colorHandler.getPrimaryColor();

        Inventory inv = event.getInventory();

        if (inv == null || !event.getView().title().equals(Component.text("Edit Server MOTD"))) return;

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        MotdHandler motdHandler = plugin.getMotdHandler();
        MotdGui gui = new MotdGui(plugin, player);

        int slot = event.getRawSlot();

        if (slot == 26) { // Add new line button
            if (motdHandler.getMotdLines().size() >= 2) {
                player.sendMessage(Component.text("You can only have 2 MOTD lines.", NamedTextColor.RED));
                return;
            }
            player.closeInventory();
            player.sendMessage(Component.text("Type the new MOTD line in chat:", primary));
            plugin.getServer().getPluginManager().registerEvents(new MotdChatListener(plugin, player, gui, true, -1), plugin);
            return;
        }

        if (slot >= 0 && slot < 25) {
            List<String> motdLines = motdHandler.getMotdLines();

            if (slot >= motdLines.size()) return;

            if (event.isRightClick()) {
                player.closeInventory();
                player.sendMessage(Component.text("Type the new text for line #" + (slot + 1) + " in chat:", primary));
                plugin.getServer().getPluginManager().registerEvents(new MotdChatListener(plugin, player, gui, false, slot), plugin);
            } else if (event.isLeftClick()) {
                motdHandler.removeLine(slot);
                player.sendMessage(Component.text("Removed MOTD line #" + (slot + 1), NamedTextColor.RED));
                gui.setupInventory();
                gui.open();
            }
        }
    }

}

package me.display.core.gui;

import me.display.core.Core;
import me.display.core.config.ColorHandler;
import me.display.core.config.MotdHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MotdGui {

    private static final int SIZE = 27; // 3 rows
    private static final int ADD_LINE_SLOT = 26; // last slot for Add Line button

    private final Core plugin;
    private final Player player;
    private final Inventory inv;
    private final MotdHandler motdHandler;

    public MotdGui(Core plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
        this.motdHandler = plugin.getMotdHandler();
        this.inv = Bukkit.createInventory(null, SIZE, Component.text("Edit Server MOTD"));
        setupInventory();
    }

    public void setupInventory() {
        inv.clear();

        // Access configured colors
        ColorHandler colorHandler = Core.getInstance().getColorHandler();

        var primary = colorHandler.getPrimaryColor();
        List<String> motdLines = motdHandler.getMotdLines();

        for (int i = 0; i < 25; i++) {
            if (i < motdLines.size()) {
                ItemStack paper = new ItemStack(Material.PAPER);
                ItemMeta meta = paper.getItemMeta();

                Component coloredLine = LegacyComponentSerializer.legacyAmpersand().deserialize(motdLines.get(i));
                meta.displayName(coloredLine);

                meta.lore(List.of(
                        Component.text("Left-click to remove this line").color(NamedTextColor.RED),
                        Component.text("Right-click to edit this line").color(NamedTextColor.YELLOW)
                ));
                paper.setItemMeta(meta);
                inv.setItem(i, paper);
            } else {
                inv.setItem(i, null);
            }
        }


        // Add New Line button
        ItemStack addLine = new ItemStack(Material.GREEN_WOOL);
        ItemMeta addMeta = addLine.getItemMeta();
        addMeta.displayName(Component.text("Add New Line").color(NamedTextColor.GREEN));
        addLine.setItemMeta(addMeta);
        inv.setItem(ADD_LINE_SLOT, addLine);
    }

    public void open() {
        player.openInventory(inv);
    }

    public Inventory getInventory() {
        return inv;
    }
}
package me.display.core.command;

import me.display.core.Core;
import me.display.core.config.ColorHandler;
import me.display.core.config.MotdHandler;
import me.vaperion.blade.annotation.command.Async;
import me.vaperion.blade.annotation.command.Command;
import me.vaperion.blade.annotation.command.Description;
import me.vaperion.blade.annotation.argument.Name;
import me.vaperion.blade.annotation.argument.Optional;
import me.vaperion.blade.annotation.argument.Sender;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

import static net.kyori.adventure.text.Component.text;

public class CoreCommand {

    @Async
    @Command("core")
    @Description("Core plugin main command")
    public static void core(
            @Sender CommandSender sender,
            @Name("subcommand") @Optional String subcommand
    ) {
        Core plugin = Core.getInstance();
        ColorHandler colorHandler = plugin.getColorHandler();
        var primary = colorHandler.getPrimaryColor();
        var secondary = colorHandler.getSecondaryColor();

        if ("reload".equalsIgnoreCase(subcommand)) {
            if (!sender.hasPermission("core.reload")) {
                sender.sendMessage(text("You don't have permission to use this command.").color(secondary));
                return;
            }

            plugin.reloadConfig();
            MotdHandler motdHandler = plugin.getMotdHandler();
            motdHandler.loadConfig();

            sender.sendMessage(text("Core plugin config reloaded!").color(primary));
        } else {
            sender.sendMessage(text("Usage: /core reload").color(primary));
        }
    }
}

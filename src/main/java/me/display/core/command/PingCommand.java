package me.display.core.command;

import me.display.core.Core;
import me.display.core.config.ColorHandler;
import me.vaperion.blade.annotation.argument.Name;
import me.vaperion.blade.annotation.argument.Optional;
import me.vaperion.blade.annotation.argument.Sender;
import me.vaperion.blade.annotation.command.Async;
import me.vaperion.blade.annotation.command.Command;
import me.vaperion.blade.annotation.command.Description;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.Component.text;

public class PingCommand {

    @Async
    @Command(value = {"ping", "lag", "ms"})
    @Description("Check a player's or your own ping")
    public static void ping(@Sender Player sender, @Name("player") @Optional("self") Player target) {
        if (target == null) {
            sender.sendMessage(text("Player not found.").color(NamedTextColor.RED));
            return;
        }

        int ping = target.getPing();

        // Get your config-defined colors
        ColorHandler colorHandler = Core.getInstance().getColorHandler();
        var primary = colorHandler.getPrimaryColor();
        var secondary = colorHandler.getSecondaryColor();

        if (sender.equals(target)) {
            sender.sendMessage(
                    text("Your ping is ", primary)
                            .append(text(ping + " ", secondary))
                            .append(text("ms", primary))
            );
        } else {
            sender.sendMessage(
                    text(target.getName(), secondary)
                            .append(text("'s ping is ", primary))
                            .append(text(ping + " ", secondary))
                            .append(text("ms", primary))
            );
        }
    }
}

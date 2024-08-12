package me.display.core.command;

import me.vaperion.blade.annotation.argument.Name;
import me.vaperion.blade.annotation.argument.Optional;
import me.vaperion.blade.annotation.argument.Sender;
import me.vaperion.blade.annotation.command.Async;
import me.vaperion.blade.annotation.command.Command;
import me.vaperion.blade.annotation.command.Description;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.LIGHT_PURPLE;
import static net.kyori.adventure.text.format.NamedTextColor.YELLOW;

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

        if (sender.equals(target)) {
            sender.sendMessage(text("Your ping is ", YELLOW)
                    .append(text(ping + " ", LIGHT_PURPLE))
                    .append(text("ms", YELLOW)));
        } else {
            sender.sendMessage(text(target.getName(), LIGHT_PURPLE).append(text(" 's ping is", YELLOW)).append(text(ping + " ", LIGHT_PURPLE)).append(text("ms", YELLOW)));
        }
    }
}

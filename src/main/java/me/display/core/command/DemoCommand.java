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

public class DemoCommand {

    @Async
    @Command(value = "demo")
    @Description("Check a player's or your own ping")
    public static void demo(@Sender Player sender, @Name("player") @Optional("self") Player target) {
        if (target == null) {
            sender.sendMessage(text("Player not found.").color(NamedTextColor.RED));
            return;
        }

        target.showDemoScreen();

        sender.sendMessage(text("You have sent ", YELLOW).append(text(target.getName(), LIGHT_PURPLE)).append(text(" the demo screen.", YELLOW)));
    }
}

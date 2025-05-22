package me.display.core.command;

import me.display.core.Core;
import me.display.core.config.ColorHandler;
import me.display.core.gui.MotdGui;
import me.vaperion.blade.annotation.command.Command;
import me.vaperion.blade.annotation.command.Description;
import me.vaperion.blade.annotation.argument.Sender;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.Component.text;

public class MotdCommand {

    @Command("motd")
    @Description("Open the MOTD editor GUI")
    public static void motdedit(@Sender Player sender) {
        MotdGui gui = new MotdGui(Core.getInstance(), sender);

        ColorHandler colorHandler = Core.getInstance().getColorHandler();

        var primary = colorHandler.getPrimaryColor();

        gui.open();
        sender.sendMessage(
                text("Opening MOTD gui", primary)
        );
    }
}

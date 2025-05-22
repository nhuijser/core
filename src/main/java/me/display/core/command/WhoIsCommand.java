package me.display.core.command;

import me.display.core.Core;
import me.display.core.config.ColorHandler;
import me.vaperion.blade.annotation.argument.Name;
import me.vaperion.blade.annotation.argument.Sender;
import me.vaperion.blade.annotation.command.Command;
import me.vaperion.blade.annotation.command.Description;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

import static net.kyori.adventure.text.Component.text;

public class WhoIsCommand {

    @Command("whois")
    @Description("Show info about a player")
    public static void whois(@Sender Player sender, @Name("player") Player target) {
        ColorHandler colorHandler = Core.getInstance().getColorHandler();

        sender.sendMessage(text("Information about ", colorHandler.getPrimaryColor())
                .append(text(target.getName(), colorHandler.getSecondaryColor())));

        sender.sendMessage(text("UUID: ", colorHandler.getPrimaryColor())
                .append(text(target.getUniqueId().toString(), colorHandler.getSecondaryColor())));

        sender.sendMessage(text("IP: ", colorHandler.getPrimaryColor())
                .append(text(target.getAddress().getAddress().getHostAddress(), colorHandler.getSecondaryColor())));

        sender.sendMessage(text("Ping: ", colorHandler.getPrimaryColor())
                .append(text(target.getPing() + "ms", colorHandler.getSecondaryColor())));

        sender.sendMessage(text("World: ", colorHandler.getPrimaryColor())
                .append(text(target.getWorld().getName(), colorHandler.getSecondaryColor())));

        sender.sendMessage(text("Location: ", colorHandler.getPrimaryColor())
                .append(text(String.format("%.1f, %.1f, %.1f",
                        target.getLocation().getX(),
                        target.getLocation().getY(),
                        target.getLocation().getZ()), colorHandler.getSecondaryColor())));

        String time = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date());
        sender.sendMessage(text("Time Checked: ", colorHandler.getPrimaryColor())
                .append(text(time, colorHandler.getSecondaryColor())));
    }
}

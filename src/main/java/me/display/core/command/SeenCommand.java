package me.display.core.command;

import me.display.core.Core;
import me.display.core.config.ColorHandler;
import me.vaperion.blade.annotation.argument.Name;
import me.vaperion.blade.annotation.argument.Sender;
import me.vaperion.blade.annotation.command.Command;
import me.vaperion.blade.annotation.command.Description;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

import static net.kyori.adventure.text.Component.text;

public class SeenCommand {

    @Command("seen")
    @Description("See when a player was last online")
    public static void seen(@Sender Player sender, @Name("player") OfflinePlayer target) {
        ColorHandler colorHandler = Core.getInstance().getColorHandler();

        if (target.isOnline()) {
            sender.sendMessage(text(target.getName() + " is currently online.", colorHandler.getPrimaryColor()));
        } else {
            long lastSeen = target.getLastSeen();
            String formatted = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date(lastSeen));
            sender.sendMessage(text(target.getName() + " was last seen at " + formatted + ".", colorHandler.getSecondaryColor()));
        }
    }
}

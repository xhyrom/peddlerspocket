package me.xhyrom.peddlerspocket.commands;

import dev.jorel.commandapi.CommandAPICommand;
import me.xhyrom.peddlerspocket.ui.SellUI;
import org.bukkit.entity.Player;

public class PeddlersCommand {
    public static void register() {
        new CommandAPICommand("peddlers")
                .withAliases("peddlerspocket", "sell")
                .executesPlayer(PeddlersCommand::execute)
                .register();
    }

    public static void execute(Player player, Object[] args) {
        new SellUI().open(player);
    }
}

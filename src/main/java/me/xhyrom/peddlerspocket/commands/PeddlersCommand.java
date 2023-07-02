package me.xhyrom.peddlerspocket.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandArguments;
import me.xhyrom.peddlerspocket.ui.SellUI;
import org.bukkit.entity.Player;

public class PeddlersCommand {
    public static void register() {
        new CommandAPICommand("peddlers")
                .withAliases("peddlerspocket", "sell")
                .executesPlayer(PeddlersCommand::execute)
                .register();
    }

    public static void execute(Player player, CommandArguments args) {
        new SellUI().open(player);
    }
}

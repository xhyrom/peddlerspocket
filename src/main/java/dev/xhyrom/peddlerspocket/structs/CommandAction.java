package dev.xhyrom.peddlerspocket.structs;

import dev.xhyrom.peddlerspocket.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandAction implements Action {
    private final String command;

    public CommandAction(String command) {
        this.command = command;
    }

    @Override
    public void execute(Player player, double result) {
        Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                command
                        .replace("<player>", player.getName())
                        .replace("<price>", String.valueOf(result))
                        .replace("<price_formatted>", Util.format(result))
        );
    }
}

package me.xhyrom.peddlerspocket.structs;

import me.xhyrom.peddlerspocket.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandAction implements Action {
    private String command;

    public CommandAction(String command) {
        this.command = command;
    }

    @Override
    public void execute(Player player, double result) {
        Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                command
                        .replace("<player>", player.getName())
                        .replace("<price>", Utils.removeDecimalIfZero(String.valueOf(result)))
                        .replace("<price_formatted>", Utils.removeDecimalIfZero(Utils.format(result)))
        );
    }
}

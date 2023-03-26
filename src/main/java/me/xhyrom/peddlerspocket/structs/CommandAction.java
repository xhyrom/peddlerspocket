package me.xhyrom.peddlerspocket.structs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandAction implements Action {
    private String command;

    public CommandAction(String command) {
        this.command = command;
    }

    @Override
    public void execute(Player player, int result) {
        Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                command
                        .replace("<player>", player.getName())
                        .replace("<price>", String.valueOf(result))
        );
    }
}

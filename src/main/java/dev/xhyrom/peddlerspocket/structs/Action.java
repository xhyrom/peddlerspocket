package dev.xhyrom.peddlerspocket.structs;

import org.bukkit.entity.Player;

public interface Action {
    void execute(Player player, double result);
}

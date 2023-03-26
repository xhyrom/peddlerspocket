package me.xhyrom.peddlerspocket.structs;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public interface Action {
    public void execute(Player player, int result);
}

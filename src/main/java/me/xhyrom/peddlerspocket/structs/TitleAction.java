package me.xhyrom.peddlerspocket.structs;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

public class TitleAction implements Action {
    private String title;
    private String subtitle = "";

    public TitleAction(String title) {
        this.title = title;
    }

    public TitleAction(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    @Override
    public void execute(Player player, int result) {
        player.showTitle(Title.title(
                MiniMessage.miniMessage().deserialize(
                        title,
                        Placeholder.parsed("price", String.valueOf(result)),
                        Placeholder.parsed("player", player.getName())
                ),
                MiniMessage.miniMessage().deserialize(
                        subtitle,
                        Placeholder.parsed("price", String.valueOf(result)),
                        Placeholder.parsed("player", player.getName())
                ))
        );
    }
}
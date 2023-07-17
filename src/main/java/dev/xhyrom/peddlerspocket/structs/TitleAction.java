package dev.xhyrom.peddlerspocket.structs;

import dev.xhyrom.peddlerspocket.utils.Util;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

public class TitleAction implements Action {
    private final String title;
    private final String subtitle;

    public TitleAction(String title, String subtitle) {
        this.title = title == null ? "" : title;
        this.subtitle = subtitle == null ? "" : subtitle;
    }

    @Override
    public void execute(Player player, double result) {
        player.showTitle(Title.title(
                MiniMessage.miniMessage().deserialize(
                        title,
                        Placeholder.parsed("price", String.valueOf(result)),
                        Placeholder.parsed("price_formatted", Util.format(result)),
                        Placeholder.parsed("player", player.getName())
                ),
                MiniMessage.miniMessage().deserialize(
                        subtitle,
                        Placeholder.parsed("price", String.valueOf(result)),
                        Placeholder.parsed("price_formatted", Util.format(result)),
                        Placeholder.parsed("player", player.getName())
                ))
        );
    }
}
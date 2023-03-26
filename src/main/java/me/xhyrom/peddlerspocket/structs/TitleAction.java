package me.xhyrom.peddlerspocket.structs;

import me.xhyrom.peddlerspocket.utils.Utils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

public class TitleAction implements Action {
    private String title;
    private String subtitle = "";

    public TitleAction(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    @Override
    public void execute(Player player, double result) {
        player.showTitle(Title.title(
                MiniMessage.miniMessage().deserialize(
                        title,
                        Placeholder.parsed("price", Utils.moveCurrencySymbol(
                                Utils.removeDecimalIfZero(
                                        String.valueOf(result)
                                )
                        )),
                        Placeholder.parsed("price_formatted", Utils.moveCurrencySymbol(
                                Utils.removeDecimalIfZero(
                                        Utils.format(result)
                                )
                        )),
                        Placeholder.parsed("player", player.getName())
                ),
                MiniMessage.miniMessage().deserialize(
                        subtitle,
                        Placeholder.parsed("price", Utils.moveCurrencySymbol(
                                Utils.removeDecimalIfZero(
                                        String.valueOf(result)
                                )
                        )),
                        Placeholder.parsed("price_formatted", Utils.moveCurrencySymbol(
                                Utils.removeDecimalIfZero(
                                        Utils.format(result)
                                )
                        )),
                        Placeholder.parsed("player", player.getName())
                ))
        );
    }
}
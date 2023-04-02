package me.xhyrom.peddlerspocket.structs;

import me.xhyrom.peddlerspocket.utils.Utils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

public class MessageAction implements Action {
    private String message;

    public MessageAction(String message) {
        this.message = message;
    }

    @Override
    public void execute(Player player, double result) {
        player.sendMessage(MiniMessage.miniMessage().deserialize(
                this.message,
                Placeholder.parsed("price", String.valueOf(result)),
                Placeholder.parsed("price_formatted", Utils.moveCurrencySymbol(
                        Utils.removeDecimalIfZero(
                                Utils.format(result)
                        )
                )),
                Placeholder.parsed("player", player.getName())
        ));
    }
}
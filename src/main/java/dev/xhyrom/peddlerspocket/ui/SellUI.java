package dev.xhyrom.peddlerspocket.ui;

import dev.xhyrom.peddlerspocket.PeddlersPocket;
import dev.xhyrom.peddlerspocket.api.PeddlersPocketAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class SellUI implements Listener {
    private Inventory inventory;

    public void open(Player player) {
        int size = 9;
        if (player.hasPermission("peddlerspocket.gui.giant")) size = 54;
        else if (player.hasPermission("peddlerspocket.gui.huge")) size = 45;
        else if (player.hasPermission("peddlerspocket.gui.large")) size = 36;
        else if (player.hasPermission("peddlerspocket.gui.medium")) size = 27;
        else if (player.hasPermission("peddlerspocket.gui.small")) size = 18;

        inventory = Bukkit.createInventory(null, size, MiniMessage.miniMessage().deserialize(
                Objects.requireNonNull(PeddlersPocket.getInstance().getConfig().getString("ui.title"))
        ));

        Bukkit.getPluginManager().registerEvents(this, PeddlersPocket.getInstance());

        player.openInventory(inventory);
    }

    public void close(Player player) {
        PeddlersPocketAPI.sell(player, inventory.getContents()).forEach(itemStack -> player.getInventory().addItem(itemStack));

        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory() != inventory) return;
        close((Player) event.getPlayer());
    }
}

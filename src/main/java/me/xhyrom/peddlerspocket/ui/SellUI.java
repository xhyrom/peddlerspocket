package me.xhyrom.peddlerspocket.ui;

import me.xhyrom.peddlerspocket.PeddlersPocket;
import me.xhyrom.peddlerspocket.api.PeddlersPocketAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class SellUI implements Listener {
    private Inventory inventory;

    public void open(Player player) {
        int size = 9;
        if (player.hasPermission("peddlerspocket.small")) size = 18;
        if (player.hasPermission("peddlerspocket.medium")) size = 27;
        if (player.hasPermission("peddlerspocket.large")) size = 36;
        if (player.hasPermission("peddlerspocket.huge")) size = 45;
        if (player.hasPermission("peddlerspocket.giant")) size = 54;

        inventory = Bukkit.createInventory(null, size, MiniMessage.miniMessage().deserialize(
                PeddlersPocket.getInstance().config.getString("ui.title")
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

package me.xhyrom.peddlerspocket.ui;

import me.xhyrom.peddlerspocket.PeddlersPocket;
import me.xhyrom.peddlerspocket.structs.Action;
import me.xhyrom.peddlerspocket.structs.Result;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
        int result = 0;
        for (ItemStack item : inventory.getContents()) {
            if (item == null) continue;
            if (item.getType() == Material.AIR) continue;

            if (PeddlersPocket.getInstance().getPrices().containsKey(item.getType())) {
                int price = PeddlersPocket.getInstance().getPrices().get(item.getType()) * item.getAmount();
                result += price;
            } else {
                player.getInventory().addItem(item);
            }
        }

        if (result > 0) {
            for (Action action : PeddlersPocket.getInstance().getActions().get(Result.SUCCESS)) {
                action.execute(player, result);
            }
        } else {
            for (Action action : PeddlersPocket.getInstance().getActions().get(Result.FAIL)) {
                action.execute(player, result);
            }
        }

        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory() != inventory) return;
        close((Player) event.getPlayer());
    }
}

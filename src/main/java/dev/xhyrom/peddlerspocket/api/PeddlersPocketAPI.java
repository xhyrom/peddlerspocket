package dev.xhyrom.peddlerspocket.api;

import dev.xhyrom.peddlerspocket.PeddlersPocket;
import dev.xhyrom.peddlerspocket.structs.Result;
import dev.xhyrom.peddlerspocket.structs.Action;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PeddlersPocketAPI {
    public static ArrayList<ItemStack> sell(Player player, ItemStack[] items) {
        ArrayList<ItemStack> notSold = new ArrayList<>();

        double result = 0;
        for (ItemStack item : items) {
            if (item == null) continue;
            if (item.getType() == Material.AIR) continue;

            if (PeddlersPocket.getInstance().getPrices().containsKey(item.getType())) {
                double price = PeddlersPocket.getInstance().getPrices().get(item.getType()) * item.getAmount();
                result += price;
            } else {
                notSold.add(item);
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

        return notSold;
    }
}

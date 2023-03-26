package me.xhyrom.peddlerspocket.utils;

import me.xhyrom.peddlerspocket.PeddlersPocket;

import java.text.NumberFormat;
import java.util.Currency;

enum Side {
    LEFT,
    RIGHT
}

public class Utils {
    public static String format(double amount) {
        final NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(2);
        format.setCurrency(
                Currency.getInstance(
                        PeddlersPocket.getInstance().config.getString("currency")
                )
        );

        return format.format(amount);
    }

    public static String moveCurrencySymbol(String amount) {
        final String config_side = PeddlersPocket.getInstance().config.getString("currency-symbol-side");
        final Side side = config_side.equalsIgnoreCase("right") ? Side.RIGHT : Side.LEFT;

        return moveCurrencySymbol(amount, side);
    }

    public static String moveCurrencySymbol(String amount, Side side) {
        final String symbol = Currency.getInstance(
                PeddlersPocket.getInstance().config.getString("currency")
        ).getSymbol();
        if (side == Side.LEFT) {
            amount = amount.replace(symbol, "");
            amount = symbol + amount;
        } else {
            amount = amount.replace(symbol, "");
            amount = amount + symbol;
        }

        return amount;
    }

    public static String removeDecimalIfZero(String amount) {
        if (amount.endsWith(".00")) {
            amount = amount.substring(0, amount.length() - 3);
        }

        return amount;
    }
}

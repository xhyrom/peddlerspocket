package me.xhyrom.peddlerspocket.utils;

import me.xhyrom.peddlerspocket.PeddlersPocket;

import java.text.NumberFormat;
import java.util.Currency;

public class Utils {
    public static String format(double amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(2);
        format.setCurrency(
                Currency.getInstance(
                        PeddlersPocket.getInstance().config.getString("currency")
                )
        );

        return format.format(amount);
    }

    public static String removeDecimalIfZero(String amount) {
        if (amount.endsWith(".00")) {
            amount = amount.substring(0, amount.length() - 3);
        }

        return amount;
    }
}

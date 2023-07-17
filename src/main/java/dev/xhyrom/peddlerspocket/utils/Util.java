package dev.xhyrom.peddlerspocket.utils;

import java.text.NumberFormat;
import java.util.Currency;

public class Util {
    public static String format(double amount) {
        final NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(2);
        format.setCurrency(Currency.getInstance("USD"));

        return removeDecimalIfZero(format.format(amount).substring(1));
    }

    public static String removeDecimalIfZero(String amount) {
        if (amount.endsWith(".00")) {
            amount = amount.substring(0, amount.length() - 3);
        }

        return amount;
    }
}

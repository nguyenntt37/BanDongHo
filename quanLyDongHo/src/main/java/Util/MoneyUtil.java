/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author Nguyen
 */
public class MoneyUtil {

    public static Long formatMoney(String money) {
        String moneyReplace = money.replace(".", "");
        return Long.parseLong(moneyReplace);
    }

    public static String formatMoney1(BigDecimal money) {
        Locale lo = new Locale("vi", "VN");
        DecimalFormat fomat = (DecimalFormat) DecimalFormat.getCurrencyInstance(lo);
        DecimalFormatSymbols formatSyblos = new DecimalFormatSymbols();
        formatSyblos.setCurrencySymbol("");
        fomat.setDecimalFormatSymbols(formatSyblos);
        String t = fomat.format(money);
        return t;
    }

    public static String formatMoneyVND(BigDecimal money) {
        Locale lo = new Locale("vi", "VN");
        DecimalFormat fomat = (DecimalFormat) DecimalFormat.getCurrencyInstance(lo);
        DecimalFormatSymbols formatSyblos = new DecimalFormatSymbols();
        formatSyblos.setCurrencySymbol("VND");
        fomat.setDecimalFormatSymbols(formatSyblos);
        String t = fomat.format(money);
        return t;
    }
}

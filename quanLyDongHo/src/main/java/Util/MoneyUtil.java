/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Nguyen
 */
public class MoneyUtil {

    static Locale localeVN = new Locale("vi", "VN");
    static NumberFormat vn = NumberFormat.getInstance(localeVN);

    public static String formatMoney(BigDecimal money) {
        return vn.format(money);
    }

    public static Long removeDecimalPart(String money) {
        String moneyReplace = money.replace(".00", "");
        return Long.valueOf(moneyReplace);
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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.math.BigDecimal;

/**
 *
 * @author acer
 */
public class Validate {

    public static boolean checkEmtry(String... strings) {
        for (String string : strings) {
            if ("".equals(string)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkFormatDouble(String... strings) {
        for (String string : strings) {
            try {
                Double.parseDouble(string);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkFormatInt(String... strings) {
        for (String string : strings) {
            try {
                Integer.parseInt(string);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkFormatSmallerThanRezo(Double... doubles) {
        for (Double aDouble : doubles) {
            if (aDouble <= 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkName(String... strings) {
        String regex = "^(([a-zA-Z\\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦ"
                + "ẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)([a-zA-Z\\s\\'ÀÁÂÃÈÉÊÌÍ"
                + "ÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủ"
                + "ứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)([a-zA-Z\\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯ"
                + "ẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]))*$";
        for (String string : strings) {
            if (!string.matches(regex)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
    }
}

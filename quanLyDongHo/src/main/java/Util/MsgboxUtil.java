/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen
 */
public class MsgboxUtil {

    public static void alert(Component p, String m) {
        JOptionPane.showMessageDialog(p, m, "Hệ thống quản lý bán hàng", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(Component p, String m) {
        int result = JOptionPane.showConfirmDialog(p, m, "Hệ thống quản lý bán hàng", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    public static String prompt(Component p, String m) {
        return JOptionPane.showInputDialog(p, m, "Hệ thống quản lý bán hàng", JOptionPane.INFORMATION_MESSAGE);
    }
}

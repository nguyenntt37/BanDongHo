/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Nguyen
 */
public class DatetimeUtil {
    public static String getCurrentDateAndTime() {
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtm.format(now);
    }

    public static String getCurrentDate() {
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return dtm.format(now);
    }

    public static String getCurrentTime() {
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtm.format(now);
    }
}

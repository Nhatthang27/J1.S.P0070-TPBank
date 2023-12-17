/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import static controller.LoginProgram.checkAccountNumber;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author Nhật Thắng
 */
public class MyUtil {

    private static Scanner sc = new Scanner(System.in);

    public static String inputAString(String mess) {
        System.out.print(mess);
        return sc.nextLine();
    }

    public static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }

    public static int inputAnInteger(String mess, String err, int low, int up) {
        do {
            try {
                int x = Integer.parseInt(inputAString(mess));
                if (x < low || x > up) {
                    throw new NumberFormatException();
                } 
                return x;
            } catch (NumberFormatException e) {
                System.err.println(err);
            }
        } while (true);
    }
    
    public static String getMess(Locale curLocale, String key) {
        ResourceBundle mess = ResourceBundle.getBundle("resources/" + curLocale, curLocale);
        return mess.getString(key);
    }
}

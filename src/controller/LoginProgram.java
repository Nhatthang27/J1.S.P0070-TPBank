/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Random;
import util.MyUtil;
import view.Menu;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Nhật Thắng
 */
public class LoginProgram {

    private static final String pfAccNum = "\\d{10}";
    private final static String pfPassword1 = ".*[A-Za-z].*";
    private final static String pfPassword2 = ".*[0-9].*";
    private final static String pfPassword3 = ".{8,31}";

    public static String checkAccountNumber(String accountNumber, Locale curLocale) {
        if (accountNumber.matches(pfAccNum)) {
            return getMess(curLocale, "loginSuccess");
        } else {
            return getMess(curLocale, "errCheckInputAccount");
        }
    }

    public static String checkPassword(String password, Locale curLocale) {
        if (!password.matches(pfPassword1) || !password.matches(pfPassword2)) {
            return getMess(curLocale, "errCheckAlphanumericPassword");
        } else if (!password.matches(pfPassword3)) {
            return getMess(curLocale, "errCheckLengthPassword");
        } else {
            return getMess(curLocale, "loginSuccess");
        }
    }

    public static String checkCapcha(String capcha1, String capcha2, Locale curLocale) {
        if (capcha1.equals(capcha2)) {
            return getMess(curLocale, "loginSuccess");
        } else {
            return getMess(curLocale, "errCaptchaIncorrect");
        }
    }

    public static String generateCaptcha() {
        final int CAPCHA_LENGHT = 6;
        StringBuilder characters = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            characters.append((char) ('a' + i));
            characters.append((char) ('A' + i));
            if (i < 10) {
                characters.append(i);
            }
        }

        StringBuilder capcha = new StringBuilder();
        for (int i = 0; i < CAPCHA_LENGHT; i++) {
            capcha.append(characters.charAt(new Random().nextInt(characters.length())));
        }
        return capcha.toString();
    }

    public static String getMess(Locale curLocale, String key) {
        ResourceBundle mess = ResourceBundle.getBundle("resources/" + curLocale, curLocale);
        return mess.getString(key);
    }

    public static void login(Locale curLocale) {
        String accNum, password, capcha2;
        do {
            accNum = MyUtil.inputAString(getMess(curLocale, "enterAccountNumber"));
            String test = checkAccountNumber(accNum, curLocale); 
            if (test.equals(getMess(curLocale, "loginSuccess"))) {
                break;
            } else {
                System.err.println(test);
            }
        } while (true);

        do {
            password = MyUtil.inputAString(getMess(curLocale, "enterPassword"));
            String test = checkPassword(password, curLocale);
            if (test.equals(getMess(curLocale, "loginSuccess"))) {
                break;
            } else {
                System.err.println(test);
            }
        } while (true);

        do {
            String capcha1 = generateCaptcha();
            System.out.println("Capcha: " + capcha1);
            capcha2 = MyUtil.inputAString(getMess(curLocale, "enterCaptcha"));
            String test = checkCapcha(capcha1, capcha2, curLocale);
            if (test.equals(getMess(curLocale, "loginSuccess"))) {
                break;
            } else {
                System.err.println(test);
            }
        } while (true);
    }

    public static void main(String[] args) {
        Menu menu = new Menu("Login Program");
        menu.add("1. Vietnamese");
        menu.add("2. English");
        menu.add("3. Exit");

        int choice;
        do {
            menu.display();
            choice = MyUtil.inputAnInteger("Please choice one option: ",
                    "Option must be in 1 to " + menu.getNumOp(),
                    1, menu.getNumOp());
            switch (choice) {
                case 1:
                    login(new Locale("vi"));
                    break;
                case 2:
                    login(new Locale("en"));
                    break;
                case 3:
                    System.out.println("Thank You");
                    break;
            }
        } while (choice != 3);
    }

}

package user;

import tools.Request;

import java.sql.SQLOutput;
import java.util.Scanner;

public class authorization {
    public static String login;
    public static String password;
    public static boolean logins = false;

    public static Request logIn(String login, String password) {
        String nameComand = "login";
        return new Request(nameComand, login, password);
    }

    private static boolean checkString(String str) {
        if (str.trim().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

}

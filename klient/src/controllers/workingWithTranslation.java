package controllers;

import java.util.Locale;
import java.util.ResourceBundle;

public class workingWithTranslation {
    public static ResourceBundle lRU = ResourceBundle.getBundle("resurses.Resources_ru", new Locale("ru", "RU"));
    public static ResourceBundle lCZ = ResourceBundle.getBundle("resurses.Resources_cz", new Locale("cs", "CZ"));
    public static ResourceBundle lSV = ResourceBundle.getBundle("resurses.Resources_sv", new Locale("sv", "SV"));
    public static ResourceBundle lES = ResourceBundle.getBundle("resurses.Resources_es_EC", new Locale("es", "EC"));
    public static ResourceBundle mainL = lRU;

    public static String findKey(String value) {
        String key = "";
        for (String k : workingWithTranslation.mainL.keySet()) {
            if (mainL.getString(k).equals(value)) {
                key = k;
            }
        }
        return key;
    }


    public static String setLenguage() {
        String lenguage = "";
        if (workingWithTranslation.mainL.equals(workingWithTranslation.lRU)) {
            lenguage = "ru";

        }
        if (workingWithTranslation.mainL.equals(workingWithTranslation.lCZ)) {
            lenguage = "cz";
        }
        if (workingWithTranslation.mainL.equals(workingWithTranslation.lES)) {
            lenguage = "es";
        }
        if (workingWithTranslation.mainL.equals(workingWithTranslation.lSV)) {
            lenguage = "sv";
        }
        return lenguage;
    }


}

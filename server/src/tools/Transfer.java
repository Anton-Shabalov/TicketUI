package tools;

import java.util.Locale;
import java.util.ResourceBundle;

public class Transfer {
    public static ResourceBundle lRU = ResourceBundle.getBundle("resurses.Resources_ru", new Locale("ru", "RU"));
    public static ResourceBundle lCZ = ResourceBundle.getBundle("resurses.Resources_cz", new Locale("cs", "CZ"));
    public static ResourceBundle lSV = ResourceBundle.getBundle("resurses.Resources_sv", new Locale("sv", "SV"));
    public static ResourceBundle lES = ResourceBundle.getBundle("resurses.Resources_es_EC", new Locale("es", "EC"));


    public static ResourceBundle checkLenguage(String lenguage) {
        ResourceBundle resourceBundle = null;
        if (lenguage.equals("ru")) {
            resourceBundle = lRU;
        }
        if (lenguage.equals("cz")) {
            resourceBundle = lCZ;
        }
        if (lenguage.equals("es")) {
            resourceBundle = lES;
        }
        if (lenguage.equals("sv")) {
            resourceBundle = lSV;
        }
        return resourceBundle;


    }

}

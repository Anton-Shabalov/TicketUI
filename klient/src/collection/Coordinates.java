package collection;

import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * Хранит в себе координаты проведения мероприятия
 */
public class Coordinates implements Serializable {
    private int x; //Значение поля должно быть больше -951
    private double y;

    /**
     * Конструктор, который задание все поля обьекту
     *
     * @param y кордината по оси y
     * @param x кордината по оси x
     */

    public Coordinates(int x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Конструктор по умолчанию
     */
    public Coordinates() {
    }


    /**
     * Выводит все поля обьекта
     */

    @Override
    public String toString() {
        return "{" +
                "место: " + x +
                ", ряд: " + y +
                '}';
    }

    public String toString(ResourceBundle lenguage) {
        return "{" +
                lenguage.getString("Место:") + x +
                lenguage.getString("Ряд:") + y +
                '}';
    }

    /**
     * устанавливает кординату по x
     *
     * @param x
     * @return возвращает boolean в зависимости от того получилось ли установить значение или нет
     */

    public boolean setX(int x) {
        if (x > -951) {
            this.x = x;
            return true;
        } else {
            System.out.println("Значение координаты по оси x должно быть больше -951");
            return false;

        }

    }

    /**
     * устанавливает кординату по y
     *
     * @param y
     * @return возвращает boolean в зависимости от того получилось ли установить значение или нет
     */

    public boolean setY(double y) {
        this.y = y;
        return true;
    }

    public int getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

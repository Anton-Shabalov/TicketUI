package collection;

import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * Хранит в себе значения локации
 */
public class Location implements Serializable {
    private Integer x; //Поле не может быть null
    private double y;
    private Long z; //Поле не может быть null


    /**
     * конструктор который задает все поля обьекту
     *
     * @param x кордината по оси x
     * @param y кордината по оси y
     * @param z кордината по оси z
     */
    public Location(Integer x, double y, Long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * конструткор по умолчанию
     */
    public Location() {
    }

    /**
     * устанавливает кординату по y
     *
     * @param y
     */

    public void setY(double y) {
        this.y = y;
    }

    /**
     * устанавливает кординату по x
     *
     * @param x
     */

    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * устанавливает кординату по z
     *
     * @param z
     */

    public void setZ(Long z) {
        this.z = z;
    }

    /**
     * выводит все значения поля
     */
    @Override
    public String toString() {
        return "{" +
                "Кордината по x:= " + x +
                ", Кордината по y= " + y +
                ",Кордината по z= " + z +
                '}';
    }

    public String toString(ResourceBundle lenguage) {
        return "{" +
                "Кордината по x:= " + x +
                ", Кордината по y= " + y +
                ",Кордината по z= " + z +
                '}';
    }

    public Integer getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Long getZ() {
        return z;
    }
}
//execute_script /Users/antonsabalov/Downloads/laba5/src/f5

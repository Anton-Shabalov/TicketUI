package collection;

import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * Хранит в себе адрес проведения мероприятия.
 */
public class Address implements Serializable {
    private String zipCode; //Длина строки не должна быть больше 20, Поле не может быть null
    private Location town; //Поле может быть null

    /**
     * Конструктор, который задает все поля
     *
     * @param zipCode индекс
     * @param town    город
     */

    public Address(String zipCode, Location town) {
        this.town = town;
        this.zipCode = zipCode;
    }

    /**
     * Конструктор по умолчанию
     */
    public Address() {
    }

    public void setTown(Location town) {
        this.town = town;
    }

    /**
     * Устанавливает индекст
     *
     * @param zipCode индекст заведения
     */

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Выводит все поля обьекта
     */
    @Override
    public String toString() {
        return "{" +
                "индекс: " + zipCode + '\'' +
                ", Расположение: " + town +
                '}';
    }

    public String toString(ResourceBundle lenguage) {
        return "{" +
                lenguage.getString("индекс:") + zipCode + '\'' +
                lenguage.getString("Расположение") + town.toString(lenguage) +
                '}';
    }

    /**
     * Возврашает название города
     *
     * @return название города
     */

    public Location getTown() {
        return town;
    }

    public String getZipCode() {
        return zipCode;
    }
}
//    execute_script /Users/antonsabalov/Downloads/laba5/src/f5

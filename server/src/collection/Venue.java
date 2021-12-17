package collection;

import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * хранит в себе ифномацию о месте проведения мероприятия
 */
public class Venue implements Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Integer capacity; //Поле может быть null, Значение поля должно быть больше 0
    private VenueType type; //Поле может быть null
    private Address address; //Поле не может быть null

    /**
     * Конструктор, который заполняет все поля
     *
     * @param id       номер билета
     * @param name     название заведения
     * @param capacity вместимость заведения
     * @param type     тип заведения
     * @param address  обьект адресс
     */

    public Venue(int id, String name, Integer capacity, VenueType type, Address address) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.address = address;


    }

    /**
     * Конструктор по умолчанию
     */
    public Venue() {
    }

    /**
     * устанавливает id
     *
     * @param id номер билета
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * устанавливает название
     *
     * @param name номер билета
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * устанавливает вместимость заведения
     *
     * @param capacity номер билета
     */
    public boolean setCapacity(Integer capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
            return true;
        } else {
            return false;
        }
    }

    /**
     * устанавливает тип заведения
     *
     * @param type номер билета
     */
    public void setVenueType(VenueType type) {
        this.type = type;
    }

    /**
     * устанавливает обьект адреса
     *
     * @param address номер билета
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * возвращает адрес
     *
     * @return возвращает обьект адреса
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Выводит все поля
     */
    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                "Название заведения:" + name + '\'' +
                "Количество мест всего:" + capacity +
                "Вид заведения" + type +
                "Адрес:" + address +
                '}';
    }

    public String toString(ResourceBundle lenguage) {
        return "{" +
                lenguage.getString("id:") + id +
                lenguage.getString("Название заведения:") + name + '\'' +
                lenguage.getString("Количество мест всего:") + capacity +
                lenguage.getString("Вид заведения") + type +
                lenguage.getString("Адрес:") + address.toString(lenguage) +
                '}';
    }


    public Integer getCapacity() {
        return capacity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public VenueType getType() {
        return type;
    }
}


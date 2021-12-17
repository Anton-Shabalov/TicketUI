package collection;

import tools.CommandRead;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Хранит в себе значения всех данных билета
 */
public class Ticket implements Map.Entry<Integer, Ticket>, Comparable<Ticket>, Serializable {

    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private String creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private long discount; //Значение поля должно быть больше 0, Максимальное значение поля: 100
    private TicketType type; //Поле может быть null
    private Venue venue; //Поле может быть null
    private String admin;


    public Ticket(Integer id, String name, Coordinates coordinates, String creationDate, float price, long discount, TicketType type, Venue venue) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.discount = discount;
        this.type = type;
        this.venue = venue;


    }

    public Ticket(Integer id, String name, Coordinates coordinates, String creationDate, float price, long discount, TicketType type, Venue venue, String admin) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.discount = discount;
        this.type = type;
        this.venue = venue;
        this.admin = admin;

    }

    public Ticket() {
        creationDate = (new Date()).toString();
    }

    /**
     * @return возвращает скидку на билет
     */
    public long getDiscount() {
        return discount;
    }

    /**
     * @return возвращает цену на билет
     */
    public float getPrice() {
        return price;
    }

    /**
     * Выводит все поля
     */

    @Override
    public String toString() {
        return "Билет " +
                "id: " + id +
                ", Билет на: " + name + '\'' +
                ", Место :" + coordinates +
                ", Дата создания: " + creationDate +
                ", Цена: " + price +
                ", Скидка: " + discount +
                ", Тип билета: " + type +
                ", Место проведения: " + venue;
    }

    public String toString(ResourceBundle lenguage) {
        return lenguage.getString("Билет") +
                lenguage.getString("id:") + id +
                lenguage.getString("Билет на:") + name + '\'' +
                lenguage.getString("Место:") + coordinates.toString(lenguage) +
                lenguage.getString("Дата создания:") + creationDate +
                lenguage.getString("Цена:") + price +
                lenguage.getString("Скидка:") + discount +
                lenguage.getString("Тип билета:") + type +
                lenguage.getString("Место проведения:") + venue.toString(lenguage) + "\n";
    }

    /**
     * Возвращает тип билета
     */
    public TicketType getType() {
        return type;
    }

    /**
     * Возвращает id билета
     */
    public Integer getId() {
        return id;
    }

    /**
     * Возвращает название мероприятия
     */

    public int getX() {
        return coordinates.getX();
    }

    public Double getY() {
        return coordinates.getY();
    }

    public String getNames() {
        return name;
    }

    @Override
    public Integer getKey() {
        return null;
    }

    @Override
    public Ticket getValue() {
        return null;
    }

    @Override
    public Ticket setValue(Ticket value) {
        return null;
    }

    @Override
    public int compareTo(Ticket o) {
        return Integer.compare(this.getCapacity(), o.getCapacity());
    }

    public int comparePrice(Ticket o) {
        return (int) (this.price - o.price);
    }

    /**
     * устанавливает id
     *
     * @param id
     * @return выводит true или false в зависимости от получилось ли установить значение или нет
     */

    public boolean setId(Integer id) {
        if (id > 0) {
            this.id = id;
            return true;
        } else {
            return false;
        }

    }

    public String getName() {
        return name;
    }


    /**
     * устанавливает название заведения
     *
     * @param name кординаты
     * @return выводит true или false в зависимости от получилось ли установить значение или нет
     */

    public boolean setName(String name) {
        if (name != null) {
            this.name = name;
            return true;
        } else {
            return false;
        }

    }

    /**
     * устанавливает кординаты
     *
     * @param coordinates кординаты
     * @return выводит true или false в зависимости от получилось ли установить значение или нет
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * устанавливает дату создания
     *
     * @param creationDate дата создания
     * @return выводит true или false в зависимости от получилось ли установить значение или нет
     */

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * устанавливает цену на билет
     *
     * @param price цена
     * @return выводит true или false в зависимости от получилось ли установить значение или нет
     */


    public boolean setPrice(float price) {
        if (price > 0) {
            this.price = price;
            return true;
        } else {
            System.out.println("Цена не может принимать отрицательные значения");
            return false;
        }
    }

    /**
     * устанавливает скидку на билет
     *
     * @param discount скидка на билет
     * @return выводит true или false в зависимости от получилось ли установить значение или нет
     */

    public boolean setDiscount(long discount) {
        if (discount >= 0 && discount <= 100) {
            this.discount = discount;
            return true;
        } else {
            System.out.println("Скидка не может принимать значения меньше нуля и больше 100");
            return false;
        }
    }

    /**
     * устанавливает тип билета
     *
     * @param type тип билета
     */

    public void setType(TicketType type) {
        this.type = type;
    }

    /**
     * задает обьект Venue
     *
     * @param venue обьект venue
     */

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    /**
     * @return возвращает обьект Venue
     */
    public Venue getVenue() {
        return venue;
    }

    public Integer getCapacity() {
        return venue.getCapacity();
    }

    public String getVenName() {
        return venue.getName();
    }

    public VenueType getVenueType() {
        return venue.getType();
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getZipcode() {
        return venue.getAddress().getZipCode();
    }

    public Integer getLocationX() {
        return venue.getAddress().getTown().getX();
    }

    public Double getLocationY() {
        return venue.getAddress().getTown().getY();
    }

    public Long getLocationZ() {
        return venue.getAddress().getTown().getZ();
    }

    public String getDate() {
        return creationDate;
    }

}

package tools;
/**
 * Класс для создания новых элементов коллекции.
 */

import DB.WorkDB;
import collection.*;
//import network.SendingResponse;

import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Validation {


    private ConcurrentHashMap<Integer, Ticket> collection;
    private Ticket ticket;
    private Venue venue;
    private int id;
    private Location location;
    private int checkPoint = 0;
    private int passage = 0;
//   private SendingResponse sendingResponse=new SendingResponse();

    /**
     * Конструктор класса для задания коллекции для записи и элемента
     *
     * @param collection колекция
     * @param ticket     элемент коллекции
     */
    public Validation(ConcurrentHashMap<Integer, Ticket> collection, Ticket ticket) {
        this.ticket = ticket;
        this.venue = ticket.getVenue();
        this.location = venue.getAddress().getTown();
        this.collection = collection;
        this.id = ticket.getId();
    }

    /**
     * Конструктор класса для задания коллекции для записи
     *
     * @param collection колекция
     */
    public Validation(ConcurrentHashMap<Integer, Ticket> collection) {
        this.collection = collection;
        ticket = new Ticket();
        ticket.setCreationDate(new Date().toString());
        venue = new Venue();
        location = new Location();


    }

    Scanner scanner = new Scanner(System.in);

    /**
     * @param id получает на входи id для того, чтоб узнать существует ли элемент с таким же id и если нет, то проверят id на валидность
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение id возвращает true иначе false
     */
    public boolean checkId(int id) {
        checkPoint = 0;

        if (passage != 0) {
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                send("ключ может принимать только целое числовое значение ");
                return false;

            }
        }
        return true;
//        for (Map.Entry<Integer, Ticket> element : collection.entrySet()) {
//            if (element.getKey()==id){
//                checkPoint=1;
//            } }
//            if(checkPoint==0 && ticket.setId(id)){
//                venue.setId(id);
//                this.id=id;
//                return true;
//            }
//            else {
//                send("Поле id должно быть уникальным и строго больше нуля. Введенное вами значение не подходит, пожалуйста введите корректные данные");
//                passage=1;
//                return false;
//
//            }
    }

    /**
     * Метод для задания названия мероприятия
     *
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение name возвращает true иначе false
     */
    public boolean checkName() {
        send("Пожалуйста, введите название мероприятия, поле не должно быть пустым.");
        checkPoint = 0;
        String name = scanner.nextLine();
        if (name.length() > 0 && ticket.setName(name) && name.replace(" ", "").length() > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Метод для задания названия мероприятия
     *
     * @param name название мероприятия
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение name возвращает true иначе false
     */
    public boolean checkName(String name) {
        if (name.length() > 0 && ticket.setName(name) && name.replace(" ", "").length() > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Метод для задания кординат
     *
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение координат возвращает true иначе false
     */
    public boolean creatCoordinates() {
        checkPoint = 0;
        Coordinates coordinates = new Coordinates();
        send("Пожалуйста, поочередно введите место и ряд , номер места должен быть больше -951 и представлен в виде целого числа, ряд в виде числа с плавающей точкой");


        try {
            int x = Integer.parseInt(scanner.nextLine());
            double y = Double.parseDouble(scanner.nextLine());
            if (coordinates.setY(y) && coordinates.setX(x)) {
                ticket.setCoordinates(coordinates);
                return true;
            } else {

                return false;
            }
        } catch (NumberFormatException e) {
            send("Вами было введено не число");
            return false;


        }
    }

    /**
     * Метод для задания координат мероприятия
     *
     * @param x координата по оси х
     * @param y координата по оси у
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение координат возвращает true иначе false
     */
    public boolean creatCoordinates(String x, String y) {
        Coordinates coordinates = new Coordinates();
        try {
            int q = Integer.parseInt(x);
            double z = Double.parseDouble(y);
            if (coordinates.setY(z) && coordinates.setX(q)) {
                ticket.setCoordinates(coordinates);
                return true;
            } else {

                return false;
            }
        } catch (NumberFormatException e) {
            send("Было считано не число.Из за этого команда не может быть выполнена");
            return false;


        }
    }


    /**
     * Метод для задания цены билета
     *
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение цены возвращает true иначе false
     */
    public boolean checkPrice() {
        send("Пожалуйста, введите цену билета, она может принимать числовое значение с плавающей точкой ");
        try {
            float x = Float.parseFloat(scanner.nextLine());
            if (ticket.setPrice(x)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            send("Введеные вами данные не похожи на число, которое требовалось ввести");
            return false;

        }

    }

    /**
     * Метод для задания цены мероприятия
     *
     * @param x цена билета
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение цены возвращает true иначе false
     */
    public boolean checkPrice(String x) {
        try {
            float z = Float.parseFloat(x);
            if (ticket.setPrice(z)) {
                return true;
            } else {
                return false;

            }
        } catch (NumberFormatException e) {
            send("Считанные данные не похожи на число, которое требовалось ввести");
            return false;

        }

    }

    /**
     * Метод для задания скидки на билет
     *
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение скидки возвращает true иначе false
     */
    public boolean checkDiscount() {
        send("Пожалуйста, введите размер скидки в формате целого числа превышающего 0 и непревышабшего 100");
        try {
            long x = Long.parseLong(scanner.nextLine());
            if (ticket.setDiscount(x)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            send("Введеные вами данные не похожи на число, которое требовалось ввести");
            return false;

        }


    }

    /**
     * Метод для задания скидки на билет
     *
     * @param x скидка на билет.
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение скидки возвращает true иначе false
     */
    public boolean checkDiscount(String x) {
        try {
            long z = Long.parseLong(x);
            if (ticket.setDiscount(z)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            send("Считанные данные не похожи на нужное число");
            return false;

        }


    }

    /**
     * Метод для задания типа билета
     *
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение типа возвращает true иначе false
     */
    public boolean setTykeTipe() {
        send("Выберите из предложенного тип билета и введите его в командную строку VIP USUAL BUDGETARY CHEAP ");
        switch (scanner.nextLine().toLowerCase()) {
            case "vip":
                ticket.setType(TicketType.VIP);
                return true;

            case "usual":
                ticket.setType(TicketType.USUAL);
                return true;

            case "budgetary":
                ticket.setType(TicketType.BUDGETARY);
                return true;

            case "cheap":
                ticket.setType(TicketType.CHEAP);
                return true;

            default:
                send("Пожалуйста, выберите одно из предложенных значений");
                return false;


        }


    }

    /**
     * Метод для задания типа билета
     *
     * @param type тип билета
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение типа возвращает true иначе false
     */
    public boolean setTykeTipe(String type) {

        switch (type.toLowerCase()) {
            case "vip":
                ticket.setType(TicketType.VIP);
                return true;

            case "usual":
                ticket.setType(TicketType.USUAL);
                return true;

            case "budgetary":
                ticket.setType(TicketType.BUDGETARY);
                return true;

            case "cheap":
                ticket.setType(TicketType.CHEAP);
                return true;

            default:
                send("Считанного типа не существует. ");
                return false;


        }


    }

    /**
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить название завед возвращает true иначе false
     */
    public boolean checkNameVenue() {
        send("Пожалуйста, введите название заведения, где будет происходит мероприятие, название не может быть пустым");
        String x = scanner.nextLine();
        if (x.trim().length() != 0) {
            venue.setName(x);
            return true;
        } else {
            send("Вы нчиего не ввели");
            return false;
        }
    }

    /**
     * @param name название заведения
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить название завед возвращает true иначе false
     */
    public boolean checkNameVenue(String name) {
        String x = name;
        if (x.trim().length() != 0) {
            venue.setName(x);
            return true;
        } else {
            send("Считанная строка оказалась пустой");
            return false;
        }
    }

    /**
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить въместимость возвращает true иначе false
     */
    public boolean checkCapacityVenue() {
        send("Введите общее количество мест в виде целого чила");
        try {
            Integer x = Integer.parseInt(scanner.nextLine());
            if (venue.setCapacity(x)) {
                return true;
            } else {
                send("Число должно быть больше нуля ");
                return false;

            }
        } catch (NumberFormatException e) {
            send("Введеные вами данные не похожи на число, которое требовалось ввести");
            return false;

        }
    }

    /**
     * @param k вместимость помещения
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить въместимость возвращает true иначе false
     */
    public boolean checkCapacityVenue(String k) {

        try {
            Integer x = Integer.parseInt(k);
            if (venue.setCapacity(x)) {
                return true;
            } else {
                send("Считанное число должно быть больше нуля");
                return false;

            }
        } catch (NumberFormatException e) {
            send("Считанные данные не похожи на целое число.");
            return false;

        }
    }

    /**
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить тип заведения возвращает true иначе false
     */
    public boolean setVenueType() {
        send("Выберите из предложенного тип билета и введите его в командную строку PUB OPEN_AREA MALL ");
        switch (scanner.nextLine().toLowerCase()) {
            case "pub":
                venue.setVenueType(VenueType.PUB);
                return true;

            case "open_area":
                venue.setVenueType(VenueType.OPEN_AREA);
                return true;

            case "mall":
                venue.setVenueType(VenueType.MALL);
                return true;

            default:
                send("Пожалуйста, выберите одно из предложенных значений");
                return false;


        }

    }

    /**
     * Метод нужен для Растановки тайпов
     *
     * @param type тип заведения
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить тип заведения возвращает true иначе false
     */
    public boolean setVenueType(String type) {
        switch (type.toLowerCase().trim()) {
            case "pub":
                venue.setVenueType(VenueType.PUB);
                return true;

            case "open_area":
                venue.setVenueType(VenueType.OPEN_AREA);
                return true;

            case "mall":
                venue.setVenueType(VenueType.MALL);
                return true;

            default:
                send("Считанные данные не похожи ни на один тип ");
                return false;


        }

    }

    /**
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить адрес возвращает true иначе false
     */
    public boolean makeAdress() {
        Address address = new Address();
        address.setTown(location);
        send("Введите индекс, его длина не должна превышать 20 символов , также он не может быть пустым");
        String z = scanner.nextLine();
        if (z.length() < 21 && z.trim().length() > 0) {
            address.setZipCode(z);
            venue.setAddress(address);
            return true;
        } else {
            return false;
        }

    }

    /**
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить кординаты возвращает true иначе false
     */
    public boolean makeLocation() {
        send("Введите кординаты точки по осям x y z  поочередно, x и z могут принимать только целочисленные значения, у число с плавающей точкой");

        try {
            Integer x = Integer.parseInt(scanner.nextLine());
            Double y = Double.parseDouble(scanner.nextLine());
            Long z = Long.parseLong(scanner.nextLine());
            location.setX(x);

            location.setY(y);
            location.setZ(z);
            return true;
        } catch (NumberFormatException e) {
            send("Введеные вами данные не похожи на число, которое требовалось ввести");
            return false;

        }
    }


    /**
     * @param index индекс заведения
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить адрес возвращает true иначе false
     */

    public boolean makeAdress(String index) {
        Address address = new Address();
        address.setTown(location);

        String z = index;
        if (z.length() < 21 && z.trim().length() > 0) {
            address.setZipCode(z);
            venue.setAddress(address);
            return true;
        } else {
            send("Длина индекса должны составлять от 1 цифры до 20");
            return false;
        }

    }

    /**
     * @param a кордината по оси х
     * @param b кордината по оси у
     * @param c кордината по оси z
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить кординаты возвращает true иначе false
     */
    public boolean makeLocation(String a, String b, String c) {
        try {
            Integer x = Integer.parseInt(a);
            Double y = Double.parseDouble(b);
            Long z = Long.parseLong(c);
            location.setX(x);
            location.setY(y);
            location.setZ(z);
            return true;
        } catch (NumberFormatException e) {
            send("Считынные данные не похожи на число, которое необходимо было считать");
            return false;

        }
    }


    /**
     * создает отбьект Tiket и добавляет его в коллекцию
     */
    public boolean makeTiket() {
        checkPoint = 0;
        for (Map.Entry<Integer, Ticket> element : collection.entrySet()) {
            if (element.getKey() == id) {
                checkPoint = 1;
            }

        }
        if (checkPoint == 1) {

            ticket.setVenue(venue);
            collection.remove(id);
            collection.put(id, ticket);
            WorkDB.replase(id);


        } else {
            ticket.setVenue(venue);
            WorkDB.pushTicket(ticket);
            WorkDB.readDB();
        }


        return true;
    }

    public boolean makeTiket(String adminName) {
        checkPoint = 0;
        for (Map.Entry<Integer, Ticket> element : collection.entrySet()) {
            if (element.getKey() == id) {
                checkPoint = 1;
            }

        }
        if (checkPoint == 1) {

            ticket.setVenue(venue);
            ticket.setAdmin(adminName);
            collection.remove(id);
            collection.put(id, ticket);
            WorkDB.replase(id);

        } else {

            ticket.setVenue(venue);
            ticket.setAdmin(adminName);
            WorkDB.pushTicket(ticket);
            WorkDB.readDB();
        }
        return true;

    }

    private void send(String string) {
        ConnectingСollection.handlerCommand.send(string);

    }
}



package tools;
/**
 * Класс для создания новых элементов коллекции.
 */

import collection.*;

import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import controllers.workingWithTranslation;
import controllers.controllCommandPanel;
import user.authorization;

public class Validation {

    private Map<Integer, Ticket> collection;
    private Ticket ticket;
    private Venue venue;
    private int id;
    private Location location;
    private int checkPoint = 0;
    private int passage = 0;


    public Validation() {
        location = new Location();
        ticket = new Ticket();
        venue = new Venue();
        ticket.setCreationDate(new Date().toString());
        ticket.setAdmin(authorization.login);

    }

    /**
     * Конструктор класса для задания коллекции для записи и элемента
     *
     * @param collection колекция
     * @param ticket     элемент коллекции
     */
    public Validation(Map<Integer, Ticket> collection, Ticket ticket) {
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
    public Validation(Map<Integer, Ticket> collection) {
        this.collection = collection;
        ticket = new Ticket();
        ticket.setCreationDate(new Date().toString());
        venue = new Venue();
        location = new Location();
    }

    private void checkOnCommand() {

        while (!CommandRead.onBlockingComand) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!newTiket.ff) {
                Thread.currentThread().isInterrupted();
                while (true) {
                    try {
                        Thread.sleep(1000 * 1000 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        CommandRead.onBlockingComand = false;
        CommandRead.controllCommandPanel.writeAnwer(CommandRead.blockingCommand);

    }

    public static void tranlite(String str) {
        CommandRead.controllCommandPanel.writeAnwer(workingWithTranslation.mainL.getString(str));
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
                System.out.println("ключ может принимать только целое числовое значение ");
                return false;

            }
        }
        if (ticket.setId(id)) {
            venue.setId(id);
            this.id = id;
            return true;
        } else {
            System.out.println("Поле id должно быть уникальным и строго больше нуля. Введенное вами значение не подходит, пожалуйста введите корректные данные");
            passage = 1;
            return true;

        }
    }

    /**
     * Метод для задания названия мероприятия
     *
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение name возвращает true иначе false
     */
    public boolean checkName() {
        tranlite("Пожалуйста, введите название мероприятия, поле не должно быть пустым.");
        checkPoint = 0;
        checkOnCommand();
        String name = CommandRead.blockingCommand;
        if (name.length() > 0 && ticket.setName(name) && name.replace(" ", "").length() > 0) {
            return true;
        } else {
            tranlite("Вы введи пустую строку");
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
        tranlite("Пожалуйста, поочередно введите место и ряд , номер места должен быть больше -951 и представлен в виде целого числа, ряд в виде числа с плавающей точкой");
        try {
            checkOnCommand();
            int x = Integer.parseInt(CommandRead.blockingCommand);
            checkOnCommand();
            double y = Double.parseDouble(CommandRead.blockingCommand);
            if (coordinates.setY(y) && coordinates.setX(x)) {
                ticket.setCoordinates(coordinates);
                return true;
            } else {

                return false;
            }
        } catch (NumberFormatException e) {
            tranlite("Вами было введено не число");
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
    public static String stringStatus;

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
            stringStatus = ("место или ряд не чисдо ");
            return false;
        }
    }
    /**
     * Метод для задания цены билета
     *
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение цены возвращает true иначе false
     */
    public boolean checkPrice() {
        tranlite("Пожалуйста, введите цену билета, она может принимать числовое значение с плавающей точкой ");
        try {
            checkOnCommand();
            float x = Float.parseFloat(CommandRead.blockingCommand);
            if (ticket.setPrice(x)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            tranlite("Введеные вами данные не похожи на число, которое требовалось ввести");
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
            System.out.println("Считанные данные не похожи на число, которое требовалось ввести");
            return false;
        }

    }

    /**
     * Метод для задания скидки на билет
     *
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение скидки возвращает true иначе false
     */
    public boolean checkDiscount() {
        tranlite("Пожалуйста, введите размер скидки в формате целого числа превышающего 0 и непревышабшего 100");
        try {
            checkOnCommand();
            long x = Long.parseLong(CommandRead.blockingCommand);
            if (ticket.setDiscount(x)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            tranlite("Введеные вами данные не похожи на число, которое требовалось ввести");
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
            System.out.println("Считанные данные не похожи на нужное число");
            return false;

        }
    }

    /**
     * Метод для задания типа билета
     *
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить значение типа возвращает true иначе false
     */
    public boolean setTykeTipe() {
        tranlite("Выберите из предложенного тип билета и введите его в командную строку VIP USUAL BUDGETARY CHEAP ");
        checkOnCommand();
        switch (CommandRead.blockingCommand.toLowerCase()) {
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
                tranlite("Пожалуйста, выберите одно из предложенных значений");
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
                System.out.println("Считанного типа не существует. ");
                return false;


        }


    }

    /**
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить название завед возвращает true иначе false
     */
    public boolean checkNameVenue() {
        tranlite("Пожалуйста, введите название заведения, где будет происходит мероприятие, название не может быть пустым");
        checkOnCommand();
        String x = CommandRead.blockingCommand;
        if (x.trim().length() != 0) {
            venue.setName(x);
            return true;
        } else {
            tranlite("Вы нчиего не ввели");
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
            System.out.println("Считанная строка оказалась пустой");
            return false;
        }
    }

    /**
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить въместимость возвращает true иначе false
     */
    public boolean checkCapacityVenue() {
        tranlite("Введите общее количество мест в виде целого чила");
        try {
            checkOnCommand();
            Integer x = Integer.parseInt(CommandRead.blockingCommand);
            if (venue.setCapacity(x)) {
                return true;
            } else {
                tranlite("Число должно быть больше нуля ");
                return false;

            }
        } catch (NumberFormatException e) {
            tranlite("Введеные вами данные не похожи на число, которое требовалось ввести");
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
                System.out.println("Считанное число должно быть больше нуля");
                return false;

            }
        } catch (NumberFormatException e) {
            System.out.println("Считанные данные не похожи на целое число.");
            return false;

        }
    }

    /**
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить тип заведения возвращает true иначе false
     */
    public boolean setVenueType() {
        tranlite("Выберите из предложенного тип билета и введите его в командную строку PUB OPEN_AREA MALL ");
        checkOnCommand();
        switch (CommandRead.blockingCommand.toLowerCase()) {
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
                tranlite("Пожалуйста, выберите одно из предложенных значений");
                return false;
        }

    }

    /**
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
                System.out.println("Считанные данные не похожи ни на один тип ");
                return false;


        }

    }

    /**
     * @return возвращает значение boolean. Если новому обьекту удалось присвоить адрес возвращает true иначе false
     */
    public boolean makeAdress() {
        Address address = new Address();
        address.setTown(location);
        tranlite("Введите индекс, его длина не должна превышать 20 символов , также он не может быть пустым");
        checkOnCommand();
        String z = CommandRead.blockingCommand;
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
        tranlite("Введите кординаты точки по осям x y z  поочередно, x и z могут принимать только целочисленные значения, у число с плавающей точкой");

        try {
            checkOnCommand();
            Integer x = Integer.parseInt(CommandRead.blockingCommand);
            checkOnCommand();
            Double y = Double.parseDouble(CommandRead.blockingCommand);
            checkOnCommand();
            Long z = Long.parseLong(CommandRead.blockingCommand);
            location.setX(x);
            location.setY(y);
            location.setZ(z);
            return true;
        } catch (NumberFormatException e) {
            tranlite("Введеные вами данные не похожи на число, которое требовалось ввести");
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
            System.out.println("Длина индекса должны составлять от 1 цифры до 20");
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
            System.out.println("Считынные данные не похожи на число, которое необходимо было считать");
            return false;
        }
    }
    /**
     * создает отбьект Tiket и добавляет его в коллекцию
     */
    public void makeTiket() {
        ticket.setVenue(venue);
        CommandRead.makeObject("insert", "12321332", ticket);
    }
}



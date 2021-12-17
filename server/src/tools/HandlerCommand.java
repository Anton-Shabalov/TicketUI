package tools;
/**
 * Класс для обработки команд пользователя
 */

import DB.User;
import DB.WorkDB;
import collection.Ticket;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Map.Entry.comparingByValue;

public class HandlerCommand {

    private FileWorker fileWorker = ConnectingСollection.fileWorker;
    //private LinkedHashMap<Integer, String> history = new LinkedHashMap<Integer, String>();
    private static int numberCommands = 0;
    private final Gson json = new Gson();
    private ConcurrentHashMap<Integer, Ticket> collection = ConnectingСollection.collection;
    public int flagsend = 0;
    private Request sendRequest = null;

    public HandlerCommand() {
        sendRequest = new Request("");
    }

    /**
     * Конструктор для задания обьекта, который работает с файлами и коллекции
     */
    private ArrayList<String> allcomands = new ArrayList<String>();

    {
        allcomands.add("help                        : вывести справку по доступным командам");
        allcomands.add("info                        : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        allcomands.add("show                        : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        allcomands.add("insert id                   : добавить новый элемент с заданным ключом");
        allcomands.add("update id                   : обновить значение элемента коллекции, id которого равен заданному");
        allcomands.add("remove_key id               : удалить элемент из коллекции по его ключу");
        allcomands.add("clear                       : очистить коллекцию");
//        allcomands.add("save                        : сохранить коллекцию в файл");
        allcomands.add("execute_script file_name    : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        allcomands.add("exit                        : завершить программу (без сохранения в файл)");
        allcomands.add("remove_lower id             : удалить из коллекции все элементы, меньшие, чем заданный");
        allcomands.add("history                     : вывести последние 15 команд (без их аргументов)");
        allcomands.add("remove_lower_key id         : удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        allcomands.add("sum_of_discount             : вывести сумму значений поля discount для всех элементов коллекции");
        allcomands.add("filter_contains_name name   : вывести элементы, значение поля name которых содержит заданную подстроку");
        allcomands.add("print_field_descending_type : вывести значения поля type всех элементов в порядке убывания");

    }

    Scanner scanner = new Scanner(System.in);

    /**
     * Обрабатывает команду "Help"
     */
    private String login = "";
    private String password = "";

    public void login(Request request) {
        if (User.checkUser(request.getLogin(), request.getPassword())) {
            sendRequest.setAuthorized(true);
            login = request.getLogin();
            password = request.getPassword();

        } else {
            sendRequest.setAuthorized(false);
            login = "";
            password = "";
        }

    }

    public void commandsHelp() {

        String commandHelp = "";
        for (String comand : allcomands) {
            if (commandHelp.equals("")) {
                commandHelp = comand;
            } else {
                commandHelp = commandHelp + "\n" + comand;
            }

        }
        send(commandHelp);
    }

    public void commandsHelp(ResourceBundle lenguage) {

        String commandHelp = "";
        for (String comand : allcomands) {
            if (commandHelp.equals("")) {
                commandHelp = lenguage.getString(comand);
            } else {
                commandHelp = commandHelp + "\n" + lenguage.getString(comand);
            }

        }
        send(commandHelp);
    }

    /**
     * Обрабатывает команду "Info"
     */
    public void commandsInfo() {
        String send = "";
        send = "Колличество элементов в коллекции           : " + collection.size() + "\n";
        send = send + " Дата загрузки коллекции из файла            : " + WorkDB.dow.toString() + "\n";
        if (fileWorker.datePreservation == null) {
            send = send + "Сохранения коллекции в файл в текущей сессии не происходило" + "\n";
        } else {
            send = send + "Дата последнего сохранения коллекции в файл : " + WorkDB.save.toString() + "\n";
        }
        send = send + "Всего было введено валидных команд:" + numberCommands + "шт" + "\n";
        send(send);

    }

    public void commandsInfo(ResourceBundle lenguage) {
        String send = "";
        send = lenguage.getString("Колличество элементов в коллекции:") + collection.size() + "\n";
        send = send + lenguage.getString("Дата загрузки коллекции из файла:") + WorkDB.dow.toString() + "\n";
        if (fileWorker.datePreservation == null) {
            send = send + lenguage.getString("Сохранения коллекции в файл в текущей сессии не происходило") + "\n";
        } else {
            send = send + lenguage.getString("Дата последнего сохранения коллекции в файл:") + WorkDB.save.toString() + "\n";
        }
        send = send + lenguage.getString("Всего было введено валидных команд:") + numberCommands + lenguage.getString("штук") + "\n";
        send(send);

    }

    /**
     * Обрабатывает команду "Show"
     */
    public void commandsShow(ResourceBundle lenguage) {

        StringBuilder stringBuilder = new StringBuilder(" ");
        collection.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getValue).forEach(x -> stringBuilder.append("\n").append(x.toString(lenguage)));

        send(stringBuilder.toString());
    }

    public void commandsShow() {

        StringBuilder stringBuilder = new StringBuilder(" ");
        collection.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getValue).forEach(x -> stringBuilder.append("\n").append(x).toString());

        send(stringBuilder.toString());
    }

    /**
     * Обрабатывает команду "RemoveKey"
     */

    public void commandsRemoveKey(int key) {
        if (collection.get(key) != null) {
            if (!checkAuthor(key)) {
                send("Вы не имеете прав на это действие");
            } else {


                try {
                    WorkDB.removeitem(key);
                    collection.entrySet().stream().map(Map.Entry::getKey).filter(x -> x == key).forEach(collection::remove);
                } catch (Exception e) {
                }
                send("Элементы " + key + "  успешно удалены");

//        if (collection.containsKey(key)) {
//            collection.remove(key);
//            send("Элемент под номером " + key + " успешно удален");
//
//        } else {
//            send("Элемента с таким номером нет в коллекции");
//        }

            }
        } else {
            send("Элементы с указанным id не существует");
        }
    }

    public void commandsRemoveKey(int key, ResourceBundle leguage) {
        if (collection.get(key) != null) {
            if (!checkAuthor(key)) {
                send(leguage.getString("Вы не имеете прав на это действие"));
            } else {


                try {
                    WorkDB.removeitem(key);
                    collection.entrySet().stream().map(Map.Entry::getKey).filter(x -> x == key).forEach(collection::remove);
                } catch (Exception e) {
                }
                send(leguage.getString("Элементы  успешно удалены") + key);

//        if (collection.containsKey(key)) {
//            collection.remove(key);
//            send("Элемент под номером " + key + " успешно удален");
//
//        } else {
//            send("Элемента с таким номером нет в коллекции");
//        }

            }
        } else {
            send(leguage.getString("Элементы с указанным id не существует"));
        }
    }

    /**
     * Обрабатывает команду "clear"
     */
    public void commandsClear() {

        try {

            collection.entrySet().stream().map(Map.Entry::getKey).forEach(x -> {
                if (checkAuthor(x)) {
                    WorkDB.removeitem(x);
                    collection.remove(x);
                }
            });
        } catch (Exception e) {
        }


//        if (collection.containsKey(key)) {
//            collection.remove(key);
//            send("Элемент под номером " + key + " успешно удален");
//
//        } else {
//            send("Элемента с таким номером нет в коллекции");
//        }
        send("Элементы на которые у вас есть доступ полностью удалены");
    }

    public void commandsClear(ResourceBundle lenguage) {

        try {

            collection.entrySet().stream().map(Map.Entry::getKey).forEach(x -> {
                if (checkAuthor(x)) {
                    WorkDB.removeitem(x);
                    collection.remove(x);
                }
            });
        } catch (Exception e) {
        }


//        if (collection.containsKey(key)) {
//            collection.remove(key);
//            send("Элемент под номером " + key + " успешно удален");
//
//        } else {
//            send("Элемента с таким номером нет в коллекции");
//        }
        send(lenguage.getString("Элементы на которые у вас есть доступ полностью удалены"));
    }


    /**
     * Записывет команды введеные пользователем в историю
     */
//    public void historyRecord(String command) {
//        numberCommands = numberCommands + 1;
//        history.put(numberCommands, command);
//        if (numberCommands > 15) {
//            history.remove(numberCommands - 15);
//        }
//
//
//    }

    /**
     * Обрабатывает команду "History"
     */
//    public void commandsHistory() {
//        String send="";
//        for (int i = numberCommands; i >= numberCommands - 15; i--) {
//            if (history.get(i) != null) {
//                if(send.length()==0){
//                    send=history.get(i);
//                }else {
//                    send=send+"\n"+history.get(i);
//                }
//
//
//            }
//
//        }
//        send=send+"\n"+"Список введеных команд успешно выведен";
//        send(send);
//    }

    /**
     * Обрабатывает команду "Sum Of Discount"
     */
    public void commandsSumOfDiscount() {
        AtomicInteger sum = new AtomicInteger();
        collection.entrySet().stream().map(Map.Entry::getValue).forEach(x -> sum.addAndGet((int) x.getDiscount()));
        send(sum.toString());
//        long sumDiscount = 0;
//        for (Map.Entry<Integer, Ticket> element : collection.entrySet()) {
//            Ticket ticket = collection.get(element.getKey());
//            sumDiscount = (long) (sumDiscount + ticket.getDiscount() * 0.01 * ticket.getPrice());
//
//        }
//        send("Скидка на все биллеты составляет " + sumDiscount);

    }

    public void commandsUpdateTable() {
        ArrayList<Ticket> list = new ArrayList<Ticket>();

        collection.entrySet().stream().map(Map.Entry::getValue).forEach(x -> list.add(x));

        send(list);
//        long sumDiscount = 0;
//        for (Map.Entry<Integer, Ticket> element : collection.entrySet()) {
//            Ticket ticket = collection.get(element.getKey());
//            sumDiscount = (long) (sumDiscount + ticket.getDiscount() * 0.01 * ticket.getPrice());
//
//        }
//        send("Скидка на все биллеты составляет " + sumDiscount);

    }


    /**
     * Обрабатывает команду "Filter Contains Name"
     */
    public void commandsFilterContainsName(String name) {
        StringBuilder stringBuilder = new StringBuilder(" ");
        collection.entrySet().stream().map(Map.Entry::getValue).filter(x -> x.getName().toLowerCase().equals(name.toLowerCase())).forEach(x -> stringBuilder.append("\n").append(x).toString());
        send(stringBuilder.toString());
//            String send="";
//        for (Map.Entry<Integer, Ticket> element : collection.entrySet()) {
//            Ticket ticket = collection.get(element.getKey());
//            if (name.equalsIgnoreCase(ticket.getName())) {
//                if(send.length()==0){
//                    send=ticket.toString();
//                }else {
//                    send=send+"\n"+ticket.toString();
//                }
//
//            }
//
//        }
//        send=send+"\n"+"Элементы с данным именем успешно выведены";
//       send(send);
    }

    /**
     * Обрабатывает команду "Save"
     */
    public void commandsSave() {
        fileWorker.filleWrite();
        send("Коллекция успешно сохранена");
    }

    public void commandsSave(ResourceBundle lenguage) {
        fileWorker.filleWrite();
        send(lenguage.getString("Коллекция успешно сохранена"));
    }

    /**
     * Обрабатывает команду "Remove Lower Key"
     */
    public void commandsRemoveLowerKey(int key) {
        try {
            collection.entrySet().stream().map(Map.Entry::getKey).filter(x -> x < key).forEach(x ->
                    {

                        if (checkAuthor(x)) {
                            System.out.println(x);
                            WorkDB.removeitem(x);
                            collection.remove(x);
                        }
                    }
            );
        } catch (Exception e) {
        }

        send("Элементы id которых меньше чем указанный и на которые у вас есть права  успешно удалены");
//        ArrayList<Integer> listForRemove = new ArrayList<Integer>();
//        for (Map.Entry<Integer, Ticket> element : collection.entrySet()) {
//            if (element.getKey() < key) {
//                listForRemove.add(element.getKey());
//            }
//
//        }
//        for (int k : listForRemove) {
//            collection.remove(k);
//        }
//        send("Все элементы коллекции, которые меньше " + key + " успешно удалены");
    }

    public void commandsRemoveLowerKey(int key, ResourceBundle lenguage) {
        try {
            collection.entrySet().stream().map(Map.Entry::getKey).filter(x -> x < key).forEach(x ->
                    {

                        if (checkAuthor(x)) {
                            System.out.println(x);
                            WorkDB.removeitem(x);
                            collection.remove(x);
                        }
                    }
            );
        } catch (Exception e) {
        }

        send(lenguage.getString("Элементы id которых меньше чем указанный и на которые у вас есть права  успешно удалены"));
//        ArrayList<Integer> listForRemove = new ArrayList<Integer>();
//        for (Map.Entry<Integer, Ticket> element : collection.entrySet()) {
//            if (element.getKey() < key) {
//                listForRemove.add(element.getKey());
//            }
//
//        }
//        for (int k : listForRemove) {
//            collection.remove(k);
//        }
//        send("Все элементы коллекции, которые меньше " + key + " успешно удалены");
    }

    /**
     * Обрабатывает команду "Print Field Descending Type"
     */
    public void commandsPrintFieldDescendingType() {
        StringBuilder stringBuilder = new StringBuilder();
        collection.entrySet().stream().map(Map.Entry::getValue).sorted(new TiketTypeCompar()).forEach(x -> stringBuilder.append("\n").append(x).toString());

//        collection.entrySet().stream().map(Map.Entry::getValue).filter(x->x.getType()==TicketType.VIP).forEach(x->stringBuilder.append("\n").append(x).toString());
//        collection.entrySet().stream().map(Map.Entry::getValue).filter(x->x.getType()==TicketType.USUAL).forEach(x->stringBuilder.append("\n").append(x).toString());
//        collection.entrySet().stream().map(Map.Entry::getValue).filter(x->x.getType()==TicketType.BUDGETARY).forEach(x->stringBuilder.append("\n").append(x).toString());
//        collection.entrySet().stream().map(Map.Entry::getValue).filter(x->x.getType()==TicketType.CHEAP).forEach(x->stringBuilder.append("\n").append(x).toString());
        send(stringBuilder.toString());


    }

    public void commandsPrintFieldDescendingType(ResourceBundle lenguage) {
        StringBuilder stringBuilder = new StringBuilder();
        collection.entrySet().stream().map(Map.Entry::getValue).sorted(new TiketTypeCompar()).forEach(x -> stringBuilder.append("\n").append(((Ticket) x).toString(lenguage)).toString());

//        collection.entrySet().stream().map(Map.Entry::getValue).filter(x->x.getType()==TicketType.VIP).forEach(x->stringBuilder.append("\n").append(x).toString());
//        collection.entrySet().stream().map(Map.Entry::getValue).filter(x->x.getType()==TicketType.USUAL).forEach(x->stringBuilder.append("\n").append(x).toString());
//        collection.entrySet().stream().map(Map.Entry::getValue).filter(x->x.getType()==TicketType.BUDGETARY).forEach(x->stringBuilder.append("\n").append(x).toString());
//        collection.entrySet().stream().map(Map.Entry::getValue).filter(x->x.getType()==TicketType.CHEAP).forEach(x->stringBuilder.append("\n").append(x).toString());
        send(stringBuilder.toString());


    }

    private String StringcommandsPrintFieldDescendingTypeMakeString = "";

    private void commandsPrintFieldDescendingTypeMakeString(String string) {
        if (StringcommandsPrintFieldDescendingTypeMakeString.length() == 0) {
            StringcommandsPrintFieldDescendingTypeMakeString = string;
        } else {
            StringcommandsPrintFieldDescendingTypeMakeString = StringcommandsPrintFieldDescendingTypeMakeString + "\n" + string;
        }
    }

    /**
     * @param key id обьекта
     *            Обрабатывает команду "Remove Lower"
     */
    public void commandsRemoveLower(int key) {


        try {

            collection.entrySet().stream().map(Map.Entry::getKey).filter(x -> collection.get(x).getPrice() < collection.get(key).getPrice()).forEach(x -> {
                if (checkAuthor(x)) {
                    WorkDB.removeitem(x);
                    collection.remove(x);

                }
            });
        } catch (Exception e) {

        }
        send("Элементы цена, которых меньше" + collection.get(key).getPrice() + " и на которые у вас есть права успешно удалены");


//        ArrayList<Integer> removeList = new ArrayList<Integer>();
//        Ticket ticketOne = collection.get(key);
//        for (Map.Entry<Integer, Ticket> element : collection.entrySet()) {
//            if (element.getKey() != key) {
//                Ticket ticket = collection.get(element.getKey());
//                if (ticketOne.comparePrice(ticket) > 0) {
//                    removeList.add(element.getKey());
//                }
//            }
//
//        }
//        for (int removeKey : removeList) {
//            commandsRemoveKey(removeKey);
//        }
//        send("Все элементы успешно удалены.");
    }

    public void commandsRemoveLower(int key, ResourceBundle lenguage) {


        try {

            collection.entrySet().stream().map(Map.Entry::getKey).filter(x -> collection.get(x).getPrice() < collection.get(key).getPrice()).forEach(x -> {
                if (checkAuthor(x)) {
                    WorkDB.removeitem(x);
                    collection.remove(x);

                }
            });
        } catch (Exception e) {

        }
        send(lenguage.getString("Элементы цена, которых меньше") + collection.get(key).getPrice() + lenguage.getString("и на которые у вас есть права успешно удалены"));


//        ArrayList<Integer> removeList = new ArrayList<Integer>();
//        Ticket ticketOne = collection.get(key);
//        for (Map.Entry<Integer, Ticket> element : collection.entrySet()) {
//            if (element.getKey() != key) {
//                Ticket ticket = collection.get(element.getKey());
//                if (ticketOne.comparePrice(ticket) > 0) {
//                    removeList.add(element.getKey());
//                }
//            }
//
//        }
//        for (int removeKey : removeList) {
//            commandsRemoveKey(removeKey);
//        }
//        send("Все элементы успешно удалены.");
    }

    /**
     * @param key id обьекта
     *            Обрабатывает команду "Insert"
     */
    public void commandInsert(int key) {
        Validation validation = new Validation(collection);
        while (!validation.checkId(key)) {
        }
        while (!validation.checkName()) ;
        while (!validation.creatCoordinates()) ;
        while (!validation.checkPrice()) ;
        while (!validation.checkDiscount()) ;
        while (!validation.setTykeTipe()) ;
        while (!validation.checkNameVenue()) ;
        while (!validation.checkCapacityVenue()) ;
        while (!validation.setVenueType()) ;
        while (!validation.makeLocation()) ;
        while (!validation.makeAdress()) ;
        while (!validation.makeTiket()) ;
        send("элемент успешно добавлен в колекцию");
    }

    public void newUpdate(Ticket ticket) {
        WorkDB.newUpdaye(ticket);

    }

    public boolean commandInsertCheckId(Ticket ticket) {
        Validation validation = new Validation(collection);
        int key = ticket.getId();
        if (true) {
            WorkDB.pushTicket(ticket);
            WorkDB.readDB();

            send("Элемент успешно добавлен в коллекцию");
            return true;
        } else {
            send("Элемент успешно добавлен в коллекцию");
            return true;
        }

    }

    public boolean commandInsertCheckId(Ticket ticket, ResourceBundle lenguage) {
        Validation validation = new Validation(collection);
        int key = ticket.getId();
        if (true) {
            WorkDB.pushTicket(ticket);
            WorkDB.readDB();

            send(lenguage.getString("Элемент успешно добавлен в коллекцию"));
            return true;
        } else {
            send(lenguage.getString("Элемент успешно добавлен в коллекцию"));
            return true;
        }

    }


    /**
     * @param key id обьекта
     *            Обрабатывает команду "Update"
     */
    public void commandsUpdate(int key) {
        int checkpoint = 0;
        for (Map.Entry<Integer, Ticket> element : collection.entrySet()) {
            if (element.getKey() == key) {
                checkpoint = 1;
            }
        }
        if (checkpoint == 1) {
            Ticket ticket = collection.get(key);
            Validation validation = new Validation(collection, ticket);
            send("Введите номер поля, которое вы хотите изменить");
            send("Название мероприятия - 1");
            send("Место                - 2");
            send("Цена                 - 3");
            send("Скидка               - 4");
            send("Тип билета           - 5");
            send("Название заведения   - 6");
            send("Вместимость          - 7");
            send("Тип заведения        - 8");
            send("Адрес заведения      - 9");
            switch (scanner.nextLine().trim()) {
                case "1":
                    while (!validation.checkName()) {
                    }
                    while (!validation.makeTiket()) ;
                    break;
                case "2":
                    while (!validation.creatCoordinates())
                        while (!validation.makeTiket()) ;
                    break;
                case "3":
                    while (!validation.checkPrice())
                        while (!validation.makeTiket()) ;
                    break;
                case "4":
                    while (!validation.checkDiscount())
                        while (!validation.makeTiket()) ;
                    break;
                case "5":
                    while (!validation.setTykeTipe())
                        while (!validation.makeTiket()) ;
                    break;
                case "6":
                    while (!validation.checkNameVenue())
                        while (!validation.makeTiket()) ;
                    break;
                case "7":
                    while (!validation.checkCapacityVenue())
                        while (!validation.makeTiket()) ;
                    break;
                case "8":
                    while (!validation.setVenueType())
                        while (!validation.makeTiket()) ;
                    break;
                case "9":
                    while (!validation.makeLocation()) ;
                    while (!validation.makeAdress()) ;
                    while (!validation.makeTiket()) ;
                    break;
                default:
                    send("Вы ввели невалидное значение. Для повтора выполните запрос update (id)");


            }

        } else {
            send("Элемента с таким id не существует");
        }

    }

    /**
     * @param key      id обьекта
     * @param commands массив комманд
     *                 Выполняет изменение некоторого значения элемента
     */
    public void commandsUpdateExecute(int key, ArrayList commands) {
        if (!checkAuthor(key)) {
            send("У вас нет прав, на тспользование команды update " + key);
        } else {
            int checkpoint = 0;
            for (Map.Entry<Integer, Ticket> element : collection.entrySet()) {
                if (element.getKey() == key) {
                    checkpoint = 1;
                }
            }
            if (checkpoint == 1) {
                Ticket ticket = collection.get(key);
                Validation validation = new Validation(collection, ticket);

                switch ((String) commands.get(1)) {
                    case "1":
                        if (validation.checkName((String) commands.get(2))) {
                            validation.makeTiket();
                        } else {
                            send("Значение строки не подходит");
                        }
                        removeComand(2, commands);

                        break;
                    case "2":
                        if (validation.creatCoordinates((String) commands.get(2), (String) commands.get(3))) {
                            validation.makeTiket();
                            send("Значение поля было успешно обнавлено");
                        }
                        removeComand(3, commands);

                        break;
                    case "3":

                        if (validation.checkPrice((String) commands.get(2))) {
                            validation.makeTiket();
                        }
                        removeComand(2, commands);
                        break;
                    case "4":
                        if (validation.checkDiscount((String) commands.get(2))) {
                            validation.makeTiket();
                        }
                        removeComand(2, commands);
                        break;
                    case "5":
                        if (validation.setTykeTipe((String) commands.get(2))) {
                            validation.makeTiket();
                        }
                        removeComand(2, commands);

                        break;
                    case "6":
                        if (validation.checkNameVenue((String) commands.get(2))) {
                            validation.makeTiket();
                        }
                        removeComand(2, commands);
                        break;
                    case "7":
                        if (validation.checkCapacityVenue((String) commands.get(2))) {
                            validation.makeTiket();
                        }
                        removeComand(2, commands);
                        break;
                    case "8":
                        if (validation.setVenueType((String) commands.get(2))) {
                            validation.makeTiket();
                        }
                        removeComand(2, commands);
                        break;
                    case "9":
                        if (validation.makeLocation((String) commands.get(2), (String) commands.get(3), (String) commands.get(4))) {
                            if (validation.makeAdress((String) commands.get(5))) {
                                validation.makeTiket();
                            }
                        }
                        removeComand(5, commands);
                        break;
                    default:
                        send("Вы ввели невалидное значение. Для повтора выполните запрос update (id)");


                }

            } else {
                send("Элемента с таким id не существует, процесс выполнения данной команды был прерван");
            }
            send("Элемент успешно отредактирован");
        }
    }

    public void newcommandsUpdateExecute(int key, ArrayList commands) {

        if (collection.get(key) == null) {
            send("Оьтекта с таким id не существует");
        } else {
            if (checkAuthor(key)) {
                Ticket ticket = collection.get(key);
                Validation validation = new Validation(collection, ticket);

                switch ((String) commands.get(0)) {
                    case "1":
                        if (validation.checkName((String) commands.get(1))) {
                            validation.makeTiket();
                        } else {
                            send("Значение строки не подходит");
                        }
                        removeComand(2, commands);

                        break;
                    case "2":
                        if (validation.creatCoordinates((String) commands.get(1), (String) commands.get(2))) {
                            validation.makeTiket();
                            send("Значение поля было успешно обнавлено");
                        }
                        removeComand(3, commands);

                        break;
                    case "3":

                        if (validation.checkPrice((String) commands.get(1))) {
                            validation.makeTiket();
                            send("Значение поля было успешно обнавлено");
                        }
                        removeComand(2, commands);
                        break;
                    case "4":
                        if (validation.checkDiscount((String) commands.get(1))) {
                            validation.makeTiket();
                            send("Значение поля было успешно обнавлено");
                        }
                        removeComand(2, commands);
                        break;
                    case "5":
                        if (validation.setTykeTipe((String) commands.get(1))) {
                            validation.makeTiket();
                            send("Значение поля было успешно обнавлено");
                        }
                        removeComand(2, commands);

                        break;
                    case "6":
                        if (validation.checkNameVenue((String) commands.get(1))) {
                            validation.makeTiket();
                            send("Значение поля было успешно обнавлено");
                        }
                        removeComand(2, commands);
                        break;
                    case "7":
                        if (validation.checkCapacityVenue((String) commands.get(1))) {
                            validation.makeTiket();
                            send("Значение поля было успешно обнавлено");
                        }
                        removeComand(2, commands);
                        break;
                    case "8":
                        if (validation.setVenueType((String) commands.get(1))) {
                            validation.makeTiket();
                            send("Значение поля было успешно обнавлено");
                        }
                        removeComand(2, commands);
                        break;
                    case "9":
                        if (validation.makeLocation((String) commands.get(1), (String) commands.get(2), (String) commands.get(3))) {
                            if (validation.makeAdress((String) commands.get(3))) {
                                validation.makeTiket();
                                send("Значение поля было успешно обнавлено");
                            }
                        }
                        removeComand(4, commands);
                        break;
                    default:
                        send("Вы неверно выбрали поле для изменения");


                }

            } else {

                send("У вас нет прав на изменения этого обьекта");
            }

        }
    }

    public void newcommandsUpdateExecute(int key, ArrayList commands, ResourceBundle lenguage) {

        if (collection.get(key) == null) {
            send(lenguage.getString("Обьтекта с таким id не существует"));
        } else {
            if (checkAuthor(key)) {
                Ticket ticket = collection.get(key);
                Validation validation = new Validation(collection, ticket);

                switch ((String) commands.get(0)) {
                    case "1":
                        if (validation.checkName((String) commands.get(1))) {
                            validation.makeTiket();
                        } else {
                            send(lenguage.getString("Значение поля было успешно обнавлено"));
                        }
                        removeComand(2, commands);

                        break;
                    case "2":
                        if (validation.creatCoordinates((String) commands.get(1), (String) commands.get(2))) {
                            validation.makeTiket();
                            send(lenguage.getString("Значение поля было успешно обнавлено"));

                        }
                        removeComand(3, commands);

                        break;
                    case "3":

                        if (validation.checkPrice((String) commands.get(1))) {
                            validation.makeTiket();
                            send(lenguage.getString("Значение поля было успешно обнавлено"));
                        }
                        removeComand(2, commands);
                        break;
                    case "4":
                        if (validation.checkDiscount((String) commands.get(1))) {
                            validation.makeTiket();
                            send(lenguage.getString("Значение поля было успешно обнавлено"));
                        }
                        removeComand(2, commands);
                        break;
                    case "5":
                        if (validation.setTykeTipe((String) commands.get(1))) {
                            validation.makeTiket();
                            send(lenguage.getString("Значение поля было успешно обнавлено"));
                        }
                        removeComand(2, commands);

                        break;
                    case "6":
                        if (validation.checkNameVenue((String) commands.get(1))) {
                            validation.makeTiket();
                            send(lenguage.getString("Значение поля было успешно обнавлено"));
                        }
                        removeComand(2, commands);
                        break;
                    case "7":
                        if (validation.checkCapacityVenue((String) commands.get(1))) {
                            validation.makeTiket();
                            send(lenguage.getString("Значение поля было успешно обнавлено"));
                        }
                        removeComand(2, commands);
                        break;
                    case "8":
                        if (validation.setVenueType((String) commands.get(1))) {
                            validation.makeTiket();
                            send(lenguage.getString("Значение поля было успешно обнавлено"));
                        }
                        removeComand(2, commands);
                        break;
                    case "9":
                        if (validation.makeLocation((String) commands.get(1), (String) commands.get(2), (String) commands.get(3))) {
                            if (validation.makeAdress((String) commands.get(3))) {
                                validation.makeTiket();
                                send(lenguage.getString("Значение поля было успешно обнавлено"));
                            }
                        }
                        removeComand(4, commands);
                        break;
                    default:
                        send(lenguage.getString("Вы неверно выбрали поле для изменения"));


                }

            } else {

                send(lenguage.getString("У вас нет прав на изменения этого обьекта"));
            }

        }
    }

    /**
     * @param key      id обьекта
     * @param commands массив комманд
     *                 Выполняет добавление нового элемента в коллекцию из скрипта
     */
    public void commandInsertExecute(int key, ArrayList commands) {

        Validation validation = new Validation(collection);
        if (validation.checkId(key)) {
            if (validation.checkName((String) commands.get(1))) {
                if (validation.creatCoordinates((String) commands.get(2), (String) commands.get(3))) {
                    if (validation.checkPrice((String) commands.get(4))) {
                        if (validation.checkDiscount((String) commands.get(5))) {
                            if (validation.setTykeTipe((String) commands.get(6))) {
                                if (validation.checkNameVenue((String) commands.get(7))) {
                                    if (validation.checkCapacityVenue((String) commands.get(8))) {
                                        if (validation.setVenueType((String) commands.get(9))) {
                                            if (validation.makeLocation((String) commands.get(10), (String) commands.get(11), (String) commands.get(12))) {
                                                if (validation.makeAdress((String) commands.get(13))) {
                                                    if (validation.makeTiket(login)) {
                                                        send("Элемент успешно добавлен в коллекцию");
                                                        for (int i = 13; i > 0; i--) {
                                                            commands.remove(1);
                                                        }

                                                    }
                                                } else {
                                                    for (int i = 13; i > 0; i--) {
                                                        commands.remove(1);
                                                    }
                                                }

                                            } else {
                                                for (int i = 12; i > 0; i--) {
                                                    commands.remove(1);
                                                }
                                            }

                                        } else {
                                            removeComand(9, commands);
                                        }

                                    } else {
                                        removeComand(8, commands);
                                    }

                                } else {
                                    removeComand(7, commands);
                                }

                            } else {
                                removeComand(6, commands);
                            }
                        } else {
                            removeComand(5, commands);
                        }
                    } else {
                        removeComand(4, commands);
                    }
                } else {
                    removeComand(3, commands);

                }
            } else {
                removeComand(1, commands);
            }

        } else {

        }
    }


    public boolean saveElement(Ticket ticket, int id) {

        collection.put(id, ticket);
        return true;
    }

    /**
     * @param handlerCommand обьект обрабатывающий команды
     * @param filleName      название файла с командами
     *                       Обрабатывает команду "Execute Script"
     */
    public void commandsExecuteScript(HandlerCommand handlerCommand, String filleName, ResourceBundle lengeage) {
        CommandsExecuteScript commandsExecuteScript = new CommandsExecuteScript(filleName, handlerCommand, fileWorker, lengeage);
        try {
            commandsExecuteScript.startRead();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        commandsExecuteScript = null;
        System.gc();

    }

    public void newCommandsExecuteScript(ArrayList<String> arrayList, ResourceBundle lenuage) {
        CommandsExecuteScript commandsExecuteScript = new CommandsExecuteScript(arrayList, this, lenuage);
        commandsExecuteScript.startTreatment();
        commandsExecuteScript = null;
        System.gc();
    }

    /**
     * @param x   количество удаляемых команд
     * @param com массив с командами
     *            удаляет команды из скрипта
     */
    private void removeComand(int x, ArrayList com) {
        int k = x;
        while (k > 0) {
            com.remove(1);
            k = k - 1;
        }


    }

    private String d = "";
    StringBuilder stingforSend = new StringBuilder(" ");

    public void send(String string) {
        if (flagsend == 0) {
            numberCommands += 1;
            d = string;
        } else {
            d = d + "\n" + string;
        }
        sendRequest.setValue(d);
//     Request request=new Request(string);
//     SendingResponse.giveСlient(request);
    }

    public void send(ArrayList<Ticket> list) {

        sendRequest.setList(list);
        sendRequest.setValue("123");

//     Request request=new Request(string);
//     SendingResponse.giveСlient(request);
    }

    public void setAns(String ans) {
        d = ans;
    }

    public Request getAns() {
        if (sendRequest.isAuthorized()) {

        } else {
            sendRequest.setValue("");
        }


        return sendRequest;
    }

    private boolean checkAuthor(int key) {
        if (collection.get(key).getAdmin().equals(login) && User.checkUser(login, password)) {
            return true;
        } else {
            return false;
        }
    }
}




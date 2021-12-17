package tools;
/**
 * Класс для обработки команд введеных в консоль
 */
//execute_script /Users/antonsabalov/Desktop/ol

import collection.Ticket;


import controllers.controllCommandPanel;
import controllers.workingWithTranslation;
import javafx.collections.ObservableList;
import net.ServerConnection;
import user.*;
import net.*;

import java.io.*;
import java.net.SocketException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandRead {
    public static boolean conect = false;
    public static boolean status = true;
    public static boolean on = true;
    private String[] comannd;
    public static String nowComman = null;
    private boolean flag = true;
    private FileWorker fileWorker;
    public BufferedReader reader;
    public static ServerConnection serverConnection;
    public static volatile Boolean blockingCommandBoolean = false;
    public static volatile String blockingCommand = "";
    public static volatile Boolean onBlockingComand = false;
    public ExecutorService executorService = Executors.newFixedThreadPool(100);

    public CommandRead() {
        fileWorker = new FileWorker();
    }

    public CommandRead(BufferedReader reader, int port, String host) throws IOException {
        this.reader = reader;
        serverConnection = new ServerConnection(port, host);
    }

    public CommandRead(int port, String host) throws Exception {

        serverConnection = new ServerConnection(port, host);
        ServerConnection.statusBar.setText(workingWithTranslation.mainL.getString("Соединение установлено"));
        CommandRead.conect = false;
        ServerConnection.serverOn = true;
    }

    public static volatile controllers.controllCommandPanel controllCommandPanel;

    public static void setControllCommandPanel(controllers.controllCommandPanel controllCommandPanel) {
        CommandRead.controllCommandPanel = controllCommandPanel;
    }

    /**
     * Считывает команды из командной строки
     */
    public void reader(String comanda) throws SocketException {


        if (!authorization.logins) {
        } else {
            reader = null;


            if (nowComman == null) {
//    System.out.println("Введите команду, для простотра всех комманд введите help");
//    Scanner scanner =new Scanner(System.in);
//    while (!scanner.hasNextLine()) {}
                CommandRead.nowComman = comanda;


            } else {

            }

            if (blockingCommandBoolean) {

                onBlockingComand = true;
                blockingCommand = comanda;
//            System.out.println(comanda);

            } else {


                comannd = CommandRead.nowComman.toLowerCase().trim().split(" ");


                switch (comannd[0]) {
                    case "help":

                        if (checkCommndLine(0, comannd)) {
                            break;
                        }

                        makeObject(comannd[0], "null");

                        break;
                    case "exit":
                        if (checkCommndLine(0, comannd)) {
                            break;
                        }
                        System.exit(0);

                        break;
                    case "show":
                        if (checkCommndLine(0, comannd)) {
                            break;
                        }
                        makeObject(comannd[0], "null");

                        break;
                    case "remove_key":
                        if (checkCommndLine(1, comannd)) {
                            break;
                        }
                        try {
                            Integer.parseInt(comannd[1]);
                            int key = Integer.parseInt(comannd[1]);
                            makeObject(comannd[0], comannd[1]);
                        } catch (NumberFormatException e) {
                            Validation.tranlite("ключ может принимать только целое числовое значение ");
//                         System.out.println("ключ может принимать только целое числовое значение ");
                            CommandRead.nowComman = null;
                        }

                        break;
                    case "clear":
                        if (checkCommndLine(0, comannd)) {
                            break;
                        }
                        makeObject(comannd[0], "null");

                        break;
                    case "history":
                        if (checkCommndLine(0, comannd)) {
                            break;
                        }
                        makeObject(comannd[0], "null");

                        break;
                    case "sum_of_discount":
                        if (checkCommndLine(0, comannd)) {
                            break;
                        }
                        makeObject(comannd[0], "null");

                        break;
                    case "filter_contains_name":
                        if (checkCommndLine(1, comannd)) {
                            break;
                        }
                        makeObject(comannd[0], comannd[1]);

                        break;
//                 case "save":
//                     if (checkCommndLine(0, comannd)) {
//                         break;
//                     }
//                     makeObject(comannd[0], "null");
//
//                     break;
                    case "remove_lower_key":
                        if (checkCommndLine(1, comannd)) {
                            break;
                        }
                        try {
                            int key = Integer.parseInt(comannd[1]);
                            makeObject(comannd[0], comannd[1]);
                        } catch (NumberFormatException e) {
//                         System.out.println("ключ может принимать только целое числовое значение ");
                            Validation.tranlite("ключ может принимать только целое числовое значение ");
                            CommandRead.nowComman = null;
                        }

                        break;
                    case "print_field_descending_type":
                        if (checkCommndLine(0, comannd)) {
                            break;
                        }
                        makeObject(comannd[0], "null");

                        break;
                    case "remove_lower":
                        if (checkCommndLine(1, comannd)) {
                            break;
                        }
                        try {
                            int key = Integer.parseInt(comannd[1]);
                            makeObject(comannd[0], comannd[1]);
                        } catch (NumberFormatException e) {
//                         System.out.println("ключ может принимать только целое числовое значение ");
                            Validation.tranlite("ключ может принимать только целое числовое значение ");
                            CommandRead.nowComman = null;
                        }

                        break;
                    case "execute_script":
                        if (checkCommndLine(1, comannd)) {
                            break;
                        }
                        try {
                            startRead(comannd[1]);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }


                        break;
                    case "info":
                        if (checkCommndLine(0, comannd)) {
                            break;
                        }
                        makeObject(comannd[0], "null");

                        break;
                    case "insert":
                        executorService = Executors.newFixedThreadPool(10);
                        if (checkCommndLine(0, comannd)) {
                            break;
                        }
                        try {
                            int key = 123;

//
                            blockingCommandBoolean = true;
                            executorService.submit(new newTiket());
                        } catch (NumberFormatException e) {
//                         System.out.println("ключ может принимать только целое числовое значение, строго больше нуля  ");
                            Validation.tranlite("ключ может принимать только целое числовое значение, строго больше нуля ");
                            CommandRead.nowComman = null;
                        }


                        break;
                    case "update":
                        if (checkCommndLine(1, comannd)) {
                            break;
                        }
                        try {

                            blockingCommandBoolean = true;
                            if (comannd[0] == null) {
                                System.out.println(1);
                            }
                            if (comannd[1] == null) {
                                System.out.println(2);
                            }
                            if (executorService == null) {
                                System.out.println(3);
                            }
                            executorService.submit(new updateTiket(comannd[0], comannd[1]));

                        } catch (NumberFormatException e) {
//                         System.out.println("ключ может принимать только целое числовое значение, строго больше нуля  ");
                            Validation.tranlite("ключ может принимать только целое числовое значение, строго больше нуля ");

                            CommandRead.nowComman = null;

                        }

                        break;


                    default:

                        Validation.tranlite("Такой команды не существует. Воскользуйтесь help для получения всех возможных команд");
//                     System.out.println("Такой команды не существует. Воскользуйтесь help для получения всех возможных команд");
                        CommandRead.nowComman = null;
                        break;

                }


            }
        }


    }

    /**
     * Проверяет количество аргементов введеное пользователем
     *
     * @param arguments   количество аргументов
     * @param comanndLine команда введеная пользователем разбитая по пробелам
     * @return возвращает true если данная команда не имеет введеное количество аргементов
     */
    private boolean checkCommndLine(int arguments, String[] comanndLine) {
        if (arguments != comanndLine.length - 1) {
            Validation.tranlite("данная команда принемает другое количество аргументов");
//        System.out.println("данная команда принемает "+ arguments +" аргументов");
            CommandRead.nowComman = null;
            return true;
        } else {
            return false;
        }

    }

    private void makeObject(String nameComand, String arguments) {
        Request readLine = new Request(nameComand, arguments);
        readLine.setLenguage(workingWithTranslation.setLenguage());
        readLine.setLogin(authorization.login);
        readLine.setPassword(authorization.password);
        try {
//        System.out.println(serverConnection.sendCommand(readLine));

            controllCommandPanel.writeAnwer(serverConnection.sendCommand(readLine));

        } catch (SocketException k) {


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void makeObject(String nameComand, String arguments, Ticket ticket) {
        Request readLine = new Request(nameComand, arguments, ticket);
        readLine.setLenguage(workingWithTranslation.setLenguage());
        readLine.setLogin(authorization.login);
        readLine.setPassword(authorization.password);

        try {
//        System.out.println( serverConnection.sendCommand(readLine));
            controllCommandPanel.writeAnwer(serverConnection.sendCommand(readLine));
        } catch (SocketException k) {


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void makeObject(String nameComand, String arguments, String[] a) {
        Request readLine = new Request(nameComand, arguments, a);
        readLine.setLenguage(workingWithTranslation.setLenguage());
        readLine.setLogin(authorization.login);
        readLine.setPassword(authorization.password);
        try {
            controllCommandPanel.writeAnwer(serverConnection.sendCommand(readLine));
//        System.out.println(serverConnection.sendCommand(readLine));
        } catch (SocketException k) {


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Ticket> makeObject(String nameComand) {

        Request readLine = new Request(nameComand, 123);
        readLine.setLenguage(workingWithTranslation.setLenguage());
        readLine.setLogin(authorization.login);
        readLine.setPassword(authorization.password);
        try {
            serverConnection.sendCommand(readLine);


//        System.out.println(serverConnection.sendCommand(readLine));
        } catch (SocketException k) {


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return ServerConnection.lastRequest.getList();
        } catch (Exception e) {
        }
        return null;

    }

    private void makeObject(String nameCommand, ArrayList<String> command) {
        Request readline = new Request(nameCommand, command);
        readline.setLenguage(workingWithTranslation.setLenguage());
        readline.setLogin(authorization.login);
        readline.setPassword(authorization.password);
        try {
//        System.out.println(serverConnection.sendCommand(readline));
            controllCommandPanel.writeAnwer(serverConnection.sendCommand(readline));
        } catch (SocketException k) {


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void makeObject(Ticket ticket) {
        Request readline = new Request("newUpdate", "asd", ticket);
        readline.setLenguage(workingWithTranslation.setLenguage());
        readline.setLogin(authorization.login);
        readline.setPassword(authorization.password);
        try {
//        System.out.println(serverConnection.sendCommand(readline));
            System.out.println(serverConnection.sendCommand(readline));
        } catch (SocketException k) {


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void startRead(String filleName) throws FileNotFoundException {
        ArrayList<String> commands = new ArrayList<String>();
        File file = new File(filleName);
        if (FileWorker.newfileCheckAccessReader(file)) {
//execute_script /Users/antonsabalov/Desktop/test1
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                commands.add(scanner.nextLine());
            }
            makeObject("execute_script", commands);
        }

    }

    public static void login(Request request) {
        try {
            serverConnection.sendCommand(request);
            try {


                if (ServerConnection.lastRequest.isAuthorized()) {
                    authorization.logins = true;
                } else {
                    System.out.println("Вы ввели неверные данные");
                }
            } catch (Exception e) {
                System.out.println("Сбои при подключении к серверу, попробуйте еще раз чуть позже ");
            }


        } catch (SocketException k) {


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

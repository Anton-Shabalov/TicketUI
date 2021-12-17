package tools;
/**
 * Класс выполнения команды Execute Script
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CommandsExecuteScript {
    private String filleName;
    File file;
    private CommandRead commandRead = ConnectingСollection.commandRead;
    HandlerCommand handlerCommand = ConnectingСollection.handlerCommand;
    FileWorker fileWorker;
    private ArrayList<String> commands = new ArrayList<String>();
    Scanner scanner;
    String[] comannd;
    ResourceBundle leng;

    /**
     * Конструктор, который задание название файла с командами, обьект для обработки команд и обьект для работы с файлами
     */
    public CommandsExecuteScript(String filleName, HandlerCommand handlerCommand, FileWorker fileWorker, ResourceBundle leng) {
        this.filleName = filleName;
        this.handlerCommand = ConnectingСollection.handlerCommand;
        this.fileWorker = fileWorker;
        this.leng = leng;
    }

    public CommandsExecuteScript(ArrayList<String> arrayList, HandlerCommand handlerCommand, ResourceBundle leng) {
        commands = arrayList;
        this.handlerCommand = handlerCommand;
        this.leng = leng;
    }

    /**
     * Начало обработки команд с скрипта
     *
     * @throws FileNotFoundException
     */
    public void startRead() throws FileNotFoundException {
        file = new File(filleName);
        if (fileWorker.fileCheckAccessReader(file)) {

            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                commands.add(scanner.nextLine());
            }
            startTreatment();
        }

    }


    //    execute_script /Users/antonsabalov/Downloads/laba5/src/f5
    public void startTreatment() {
        handlerCommand.flagsend = 1;
        while (commands.size() > 0) {

            comannd = commands.get(0).toLowerCase().trim().split(" ");

            switch (comannd[0]) {
                case "help":
                    if (checkCommndLine(0, comannd)) {
                        break;
                    }
                    handlerCommand.commandsHelp();
                    break;
                case "exit":
                    if (checkCommndLine(0, comannd)) {
                        break;
                    }
                    //handlerCommand.historyRecord(comannd[0]);
                    break;
                case "show":
                    if (checkCommndLine(0, comannd)) {
                        break;
                    }
                    handlerCommand.commandsShow();
                    break;
                case "remove_key":
                    if (checkCommndLine(1, comannd)) {
                        break;
                    }
                    try {
                        Integer.parseInt(comannd[1]);
                        int key = Integer.parseInt(comannd[1]);
                        handlerCommand.commandsRemoveKey(key);
                    } catch (NumberFormatException e) {
                        send(leng.getString("ключ может принимать только целое числовое значение"));
                    }

                    break;
                case "clear":
                    if (checkCommndLine(0, comannd)) {
                        break;
                    }
                    handlerCommand.commandsClear();

                    break;
                case "history":
                    if (checkCommndLine(0, comannd)) {
                        break;
                    }
                    commandRead.sendHistory();
                    //handlerCommand.commandsHistory();

                    break;
                case "sum_of_discount":
                    if (checkCommndLine(0, comannd)) {
                        break;
                    }
                    handlerCommand.commandsSumOfDiscount();
                    break;
                case "filter_contains_name":
                    if (checkCommndLine(1, comannd)) {
                        break;
                    }
                    handlerCommand.commandsFilterContainsName(comannd[1]);

                    break;
                case "save":
                    if (checkCommndLine(0, comannd)) {
                        break;
                    }
                    handlerCommand.commandsSave();

                    break;
                case "remove_lower_key":
                    if (checkCommndLine(1, comannd)) {
                        break;
                    }
                    try {
                        int key = Integer.parseInt(comannd[1]);
                        handlerCommand.commandsRemoveLowerKey(key);
                    } catch (NumberFormatException e) {
                        send(leng.getString("ключ может принимать только целое числовое значение"));
                    }

                    break;
                case "print_field_descending_type":
                    if (checkCommndLine(0, comannd)) {
                        break;
                    }
                    handlerCommand.commandsPrintFieldDescendingType();

                    break;
                case "remove_lower":
                    if (checkCommndLine(1, comannd)) {
                        break;
                    }
                    try {
                        int key = Integer.parseInt(comannd[1]);
                        handlerCommand.commandsRemoveLower(key);
                    } catch (NumberFormatException e) {
                        send(leng.getString("ключ может принимать только целое числовое значение"));
                    }

                    break;
                case "execute_script":
                    if (checkCommndLine(1, comannd)) {
                        break;
                    }
                    if (filleName.equals(comannd[1])) {

                    } else {
                        handlerCommand.commandsExecuteScript(handlerCommand, comannd[1], leng);
                    }


                    break;
                case "info":
                    if (checkCommndLine(0, comannd)) {
                        break;
                    }
                    handlerCommand.commandsInfo();
                    break;
                case "insert":
                    if (checkCommndLine(0, comannd)) {
                        break;
                    }
                    try {
                        int key = 12123;
                        handlerCommand.commandInsertExecute(key, commands);
                    } catch (NumberFormatException e) {
                        send(leng.getString("ключ может принимать только целое числовое значение, строго больше нуля"));
                    }


                    break;
                case "updatetable":
                    handlerCommand.commandsUpdateTable();
                    break;
                case "update":
                    if (checkCommndLine(1, comannd)) {
                        break;
                    }
                    try {
                        int key = Integer.parseInt(comannd[1]);
                        handlerCommand.commandsUpdateExecute(key, commands);
                    } catch (NumberFormatException e) {
                        send("ключ может принимать только целое числовое значение, строго больше нуля");
                    }

                    break;
                default:
                    send(leng.getString("команды <") + comannd[0] + leng.getString("> не существует"));
                    break;


            }
            commands.remove(0);
        }
        handlerCommand.flagsend = 0;
    }

    /**
     * проверка на соответствие количества аргументов
     *
     * @param arguments   количество аргументов которое принимает команда
     * @param comanndLine масиив команд
     */
    private boolean checkCommndLine(int arguments, String[] comanndLine) {
        if (arguments != comanndLine.length - 1) {
            send("Комманда" + comanndLine[0] + "принемает" + arguments + "аргумента");
            return true;
        } else {
            return false;
        }

    }

    private void insert() {

    }

    private void send(String string) {
        handlerCommand.send(string);
    }
}







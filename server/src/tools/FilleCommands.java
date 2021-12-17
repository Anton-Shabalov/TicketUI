package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FilleCommands {
    private HandlerCommand handlerCommand;
    private String filleName;

    /**
     * Читываются данные введеные пользователем и откраляют их обработчику
     */
    public void executeDocument(String filleName, HandlerCommand handlerCommand) {
        this.filleName = filleName;
        this.handlerCommand = handlerCommand;
        try {
            File file = new File(filleName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] comannd = scanner.nextLine().toLowerCase().trim().split(" ");

                switch (comannd[0]) {
                    case "help":
                        handlerCommand.commandsHelp();
                        break;
                    case "show":
                        handlerCommand.commandsShow();
                        break;
                    case "remove_key":
                        try {
                            Integer.parseInt(comannd[1]);
                            int key = Integer.parseInt(comannd[1]);
                            handlerCommand.commandsRemoveKey(key);
                        } catch (NumberFormatException e) {
                            System.out.println("ключ может принимать только целое числовое значение ");
                        }
                        break;
                    case "clear":
                        if (checkCommndLine(0, comannd)) {
                            break;
                        }
                        handlerCommand.commandsClear();
                        break;
                    case "history":
//                      handlerCommand.commandsHistory();
                        break;
                    case "sum_of_discount":
                        handlerCommand.commandsSumOfDiscount();
                        break;
                    case "filter_contains_name":
                        handlerCommand.commandsFilterContainsName(comannd[1]);
                        break;
                    case "save":
                        handlerCommand.commandsSave();
                        break;
                    case "remove_lower_key":
                        try {
                            int key = Integer.parseInt(comannd[1]);
                            handlerCommand.commandsRemoveLowerKey(key);
                        } catch (NumberFormatException e) {
                            System.out.println("ключ может принимать только целое числовое значение ");
                        }
                        break;
                    case "print_field_descending_type":
                        if (checkCommndLine(0, comannd)) {
                            break;
                        }
                        handlerCommand.commandsPrintFieldDescendingType();
                        break;
                    case "remove_lower":
                        try {
                            int key = Integer.parseInt(comannd[1]);
                            handlerCommand.commandsRemoveLower(key);
                        } catch (NumberFormatException e) {
                            System.out.println("ключ может принимать только целое числовое значение ");
                        }
                        break;
                    case "info":
                        if (checkCommndLine(0, comannd)) {
                            break;
                        }
                        handlerCommand.commandsInfo();
//                      handlerCommand.historyRecord(comannd[0]);
                        break;
                    default:
                        System.out.println("Такой команды не существует. Воскользуйтесь help для получения всех возможных команд");
                        break;


                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("по указанному пути файла нет");

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
            System.out.println("данная команда принемает " + arguments + " аргументов");
            return true;
        } else {
            return false;
        }

    }


}

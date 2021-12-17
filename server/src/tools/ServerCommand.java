package tools;

import DB.User;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerCommand extends Thread {
    @Override
    public void run() {

        while (true) {
            ArrayList<String> help = new ArrayList<String>();
            help.add("stop          Выключение сервера");
            help.add("add           Добавть нового пользователя");
            help.add("help          Вывести список всех команд");
            help.add("remove        Удалить пользователя");
            help.add("show          Показать всех пользователей");
            Scanner scanner = new Scanner(System.in);
            String[] command = scanner.nextLine().toLowerCase().trim().split(" ");
            switch (command[0]) {
                case "help":
                    for (String str : help) {
                        System.out.println(str);
                    }
                    break;

                case "stop":
                    System.exit(0);
                    break;

                case "add":
                    User.addUser();

                    break;
                case "remove":
                    User.removeUser();
                    break;
                case "show":
                    User.soutUser();
                    break;

                default:
                    System.out.println("Вы ввели неподдерживаюмую команду");
                    break;

            }
        }
    }
}

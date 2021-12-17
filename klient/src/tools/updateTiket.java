package tools;

import java.util.Scanner;

public class updateTiket implements Runnable {
    public static String nameCommand;
    public static String argument;

    public updateTiket(String nameCommand, String argument) {
        updateTiket.nameCommand = nameCommand;
        updateTiket.argument = argument;
    }

    private static void checkOnCommand() {
        while (!CommandRead.onBlockingComand) {
        }
        CommandRead.controllCommandPanel.writeAnwer(CommandRead.blockingCommand);
        CommandRead.onBlockingComand = false;
    }

    @Override
    public void run() {
        boolean flag = true;
        String[] a = new String[5];
        Validation validation = new Validation();
        Validation.tranlite("Введите номер поля, которое вы хотите изменить");
        Validation.tranlite("Название мероприятия - 1");
        Validation.tranlite("Место                - 2");
        Validation.tranlite("Цена                 - 3");
        Validation.tranlite("Скидка               - 4");
        Validation.tranlite("Тип билета           - 5");
        Validation.tranlite("Название заведения   - 6");
        Validation.tranlite("Вместимость          - 7");
        Validation.tranlite("Тип заведения        - 8");
        Validation.tranlite("Адрес заведения      - 9");
        checkOnCommand();

        a[0] = CommandRead.blockingCommand.trim();
        switch (a[0]) {
            case "1":
                Validation.tranlite("Пожалуйста, введите название мероприятия, поле не должно быть пустым.");
                checkOnCommand();
                a[1] = CommandRead.blockingCommand.trim();
                if (validation.checkName(a[1])) {

                } else {
                    Validation.tranlite("Введеные вами данные не соответствуют шаблону");
                    flag = false;
                }
                break;
            case "2":
                Validation.tranlite("Пожалуйста, поочередно введите место и ряд , номер места должен быть больше -951 и представлен в виде целого числа, ряд в виде числа с плавающей точкой");
                checkOnCommand();
                a[1] = CommandRead.blockingCommand.trim();
                checkOnCommand();
                a[2] = CommandRead.blockingCommand.trim();
                if (validation.creatCoordinates(a[1], a[2])) {

                } else {
                    Validation.tranlite("Введеные вами данные не соответствуют шаблону");
                    flag = false;

                }
                break;
            case "3":
                Validation.tranlite("Пожалуйста, введите цену билета, она может принимать числовое значение с плавающей точкой ");
                checkOnCommand();
                a[1] = CommandRead.blockingCommand.trim();
                if (validation.checkPrice(a[1])) {

                } else {
                    Validation.tranlite("Введеные вами данные не соответствуют шаблону");
                    flag = false;
                }
                break;
            case "4":
                Validation.tranlite("Пожалуйста, введите размер скидки в формате целого числа превышающего 0 и непревышабшего 100");
                checkOnCommand();
                a[1] = CommandRead.blockingCommand.trim();
                if (validation.checkDiscount(a[1])) {

                } else {
                    Validation.tranlite("Введеные вами данные не соответствуют шаблону");
                    flag = false;
                }
                break;
            case "5":
                Validation.tranlite("Выберите из предложенного тип билета и введите его в командную строку VIP USUAL BUDGETARY CHEAP ");
                checkOnCommand();
                a[1] = CommandRead.blockingCommand.trim();
                if (validation.setTykeTipe(a[1])) {
                } else {
                    Validation.tranlite("Введеные вами данные не соответствуют шаблону");
                    flag = false;
                }
                break;
            case "6":
                Validation.tranlite("Пожалуйста, введите название заведения, где будет происходит мероприятие, название не может быть пустым");
                checkOnCommand();
                a[1] = CommandRead.blockingCommand.trim();
                if (validation.checkNameVenue(a[1])) {

                } else {
                    Validation.tranlite("Введеные вами данные не соответствуют шаблону");
                    flag = false;
                }
                break;
            case "7":
                Validation.tranlite("Введите общее количество мест в виде целого чила");
                checkOnCommand();
                a[1] = CommandRead.blockingCommand.trim();
                if (validation.checkCapacityVenue(a[1])) {

                } else {
                    Validation.tranlite("Введеные вами данные не соответствуют шаблону");
                    flag = false;
                }
                break;
            case "8":
                Validation.tranlite("Выберите из предложенного тип билета и введите его в командную строку PUB OPEN_AREA MALL ");
                checkOnCommand();
                a[1] = CommandRead.blockingCommand.trim();
                if (validation.setVenueType(a[1])) {

                } else {
                    Validation.tranlite("Введеные вами данные не соответствуют шаблону");
                    flag = false;

                }
                break;
            case "9":
                Validation.tranlite("Введите кординаты точки по осям x y z  поочередно, x и z могут принимать только целочисленные значения, у число с плавающей точкой");
                checkOnCommand();
                a[1] = CommandRead.blockingCommand.trim();
                checkOnCommand();
                a[2] = CommandRead.blockingCommand.trim();
                checkOnCommand();
                a[3] = CommandRead.blockingCommand.trim();
                if (validation.makeLocation(a[1], a[2], a[3])) {

                } else {
                    Validation.tranlite("Введеные вами данные не соответствуют шаблону");
                    flag = false;

                }
                Validation.tranlite("Введите индекс, его длина не должна превышать 20 символов , также он не может быть пустым");
                checkOnCommand();
                a[4] = CommandRead.blockingCommand.trim();
                if (validation.makeAdress(a[4])) {

                } else {
                    Validation.tranlite("Введеные вами данные не соответствуют шаблону");
                    flag = false;
                }

                break;
            default:
                flag = false;
                Validation.tranlite("Вы ввели невалидное значение. Для повтора выполните запрос update (id)!");
        }
        if (flag) {
            CommandRead.makeObject(nameCommand, argument, a);
        }
        CommandRead.blockingCommandBoolean = false;
        CommandRead.nowComman = null;
    }
}

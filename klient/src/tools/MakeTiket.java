package tools;

import collection.Ticket;

import java.util.Map;
import java.util.Scanner;

public class MakeTiket {


    public static String[] commandUpdate(int key) {
        String[] a = new String[5];

        Scanner scanner = new Scanner(System.in);
        Validation validation = new Validation();
        System.out.println("Введите номер поля, которое вы хотите изменить");
        System.out.println("Название мероприятия - 1");
        System.out.println("Место                - 2");
        System.out.println("Цена                 - 3");
        System.out.println("Скидка               - 4");
        System.out.println("Тип билета           - 5");
        System.out.println("Название заведения   - 6");
        System.out.println("Вместимость          - 7");
        System.out.println("Тип заведения        - 8");
        System.out.println("Адрес заведения      - 9");
        a[0] = scanner.nextLine().trim();
        switch (a[0]) {
            case "1":
                System.out.println("Пожалуйста, введите название мероприятия, поле не должно быть пустым.");
                a[1] = scanner.nextLine().trim();
                if (validation.checkName(a[1])) {

                } else {
                    System.out.println("Введеные вами данные не соответствуют шаблону");
                }
                break;
            case "2":
                System.out.println("Пожалуйста, поочередно введите место и ряд , номер места должен быть больше -951 и представлен в виде целого числа, ряд в виде числа с плавающей точкой");
                a[1] = scanner.nextLine().trim();
                a[2] = scanner.nextLine().trim();
                if (validation.creatCoordinates(a[1], a[2])) {

                } else {
                    System.out.println("Введеные вами данные не соответствуют шаблону");
                }
                break;
            case "3":
                System.out.println("Пожалуйста, введите цену билета, она может принимать числовое значение с плавающей точкой ");
                a[1] = scanner.nextLine().trim();
                if (validation.checkPrice(a[1])) {

                } else {
                    System.out.println("Введеные вами данные не соответствуют шаблону");
                }
                break;
            case "4":
                System.out.println("Пожалуйста, введите размер скидки в формате целого числа превышающего 0 и непревышабшего 100");
                a[1] = scanner.nextLine().trim();
                if (validation.checkDiscount(a[1])) {

                } else {
                    System.out.println("Введеные вами данные не соответствуют шаблону");
                }
                break;
            case "5":
                System.out.println("Выберите из предложенного тип билета и введите его в командную строку VIP USUAL BUDGETARY CHEAP ");
                a[1] = scanner.nextLine().trim();
                if (validation.setTykeTipe(a[1])) {

                } else {
                    System.out.println("Введеные вами данные не соответствуют шаблону");
                }

                break;
            case "6":
                System.out.println("Пожалуйста, введите название заведения, где будет происходит мероприятие, название не может быть пустым");
                a[1] = scanner.nextLine().trim();
                if (validation.checkNameVenue(a[1])) {

                } else {
                    System.out.println("Введеные вами данные не соответствуют шаблону");
                }

                break;
            case "7":
                System.out.println("Введите общее количество мест в виде целого чила");
                a[1] = scanner.nextLine().trim();
                if (validation.checkCapacityVenue(a[1])) {

                } else {
                    System.out.println("Введеные вами данные не соответствуют шаблону");
                }

                break;
            case "8":
                System.out.println("Выберите из предложенного тип билета и введите его в командную строку PUB OPEN_AREA MALL ");
                a[1] = scanner.nextLine().trim();
                if (validation.setVenueType(a[1])) {

                } else {
                    System.out.println("Введеные вами данные не соответствуют шаблону");
                }

                break;
            case "9":
                System.out.println("Введите кординаты точки по осям x y z  поочередно, x и z могут принимать только целочисленные значения, у число с плавающей точкой");
                a[1] = scanner.nextLine().trim();
                a[2] = scanner.nextLine().trim();
                a[3] = scanner.nextLine().trim();
                if (validation.makeLocation(a[1], a[2], a[3])) {

                } else {
                    System.out.println("Введеные вами данные не соответствуют шаблону");
                }
                System.out.println("Введите индекс, его длина не должна превышать 20 символов , также он не может быть пустым");
                a[4] = scanner.nextLine().trim();
                if (validation.makeAdress(a[4])) {

                } else {
                    System.out.println("Введеные вами данные не соответствуют шаблону");
                }

                break;
            default:
                System.out.println("Вы ввели невалидное значение. Для повтора выполните запрос update (id)!");

        }
        return a;
    }
}


package DB;

import com.sun.xml.internal.rngom.parse.host.Base;
import tools.HandlerCommand;

import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;

public class User {


    public static void addUser() {
        Scanner scanner = new Scanner(System.in);
        String login = null;
        String password = null;
        System.out.println("Введите логин ");
        login = scanner.nextLine();
        while (!checkString(login)) {
            login = scanner.nextLine();
        }
        if (checkHaveUser(login)) {
            System.out.println("Введите пароль");
            password = scanner.nextLine();
            while (!checkString(password)) {
                password = scanner.nextLine();
            }
            String send = "INSERT INTO users (login, password) values('" + login + "', '" + Hash.makeHash(password) + "')";
            Connection connection = DataBase.getConnection();
            try {
                Statement statement = connection.createStatement();
                statement.execute(send);
                System.out.println("Пользователь успешно добавлен");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("Не удалось добавить пользователя, так как пользователь с таким логином уже есть");
        }


    }

    public static boolean checkUser(String login, String password) {
        boolean flag = false;

        String send = "SELECT COUNT(*) AS total FROM users where login='" + login + "' AND password='" + Hash.makeHash(password) + "'";
        Connection connection = DataBase.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(send);
            while (resultSet.next()) {
                if (resultSet.getInt("total") > 0) {
                    flag = true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flag;

    }

    private static boolean checkString(String str) {
        if (str.trim().length() == 0) {
            System.out.println("Вы ввели пустую строку");
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkHaveUser(String login) {
        boolean flag = true;

        String send = "SELECT COUNT(*) AS total FROM users where login='" + login + "'";
        Connection connection = DataBase.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(send);
            while (resultSet.next()) {
                if (resultSet.getInt("total") > 0) {
                    flag = false;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flag;

    }

    public static void soutUser() {
        String send = "SELECT * FROM users";
        Connection connection = DataBase.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(send);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("login"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void removeUser() {

        String login = null;
        System.out.println("Введите логин пользователя, которого необходимо удалить");
        Scanner scanner = new Scanner(System.in);
        login = scanner.nextLine();
        while (!checkString(login)) {
            login = scanner.nextLine();
        }
        if (!checkHaveUser(login)) {
            String send = "DELETE FROM users WHERE login='" + login + "'";
            Connection connection = DataBase.getConnection();
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ;
                statement.execute(send);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("Пользователь успешно удален");

        } else {
            System.out.println("Пользователя с таким логином нет");
        }
    }

}



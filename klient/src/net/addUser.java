package net;

import tools.CommandRead;
import user.authorization;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class addUser {
    public static void addUsers() {
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
            Connection connection = connectDB.getConnection();
            try {
                Statement statement = connection.createStatement();
                statement.execute(send);
//            authorization.logins=true;
//            authorization.login=login;
//            authorization.password=password;
                System.out.println("Пользователь успешно добавлен");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("Не удалось добавить пользователя, так как пользователь с таким логином уже есть");
        }
    }

    public static boolean addUsers(String login, String password) {

        if (checkHaveUser(login)) {

            String send = "INSERT INTO users (login, password) values('" + login + "', '" + Hash.makeHash(password) + "')";
            Connection connection = connectDB.getConnection();
            try {
                Statement statement = connection.createStatement();
                statement.execute(send);
//            authorization.logins=true;
//            authorization.login=login;
//            authorization.password=password;
                System.out.println("Пользователь успешно добавлен");
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
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
        Connection connection = connectDB.getConnection();
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
}

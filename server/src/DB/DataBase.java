package DB;

import network.ConnectionAccepter;

import java.sql.*;

public class DataBase {
    private static String login = "s312988";
    private static String password = "ont372";
    //    private static String URL= "jdbc:postgresql://pg:5432/studs";
    private static String URL = "jdbc:postgresql://localhost:5432/studs";
    private static Connection connection = null;

    public static Boolean connectBase() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        try {
            connection = DriverManager
                    .getConnection(URL, login, password);

        } catch (SQLException e) {
            System.out.println("Подключение разоврано");
        }
        if (connection != null) {
            return true;
        } else {
            System.out.println("Не удалось подключиться к базе данных");
            return false;
        }


    }

    public static Connection getConnection() {
        return connection;
    }
//    public static String sendRequest(String sql){
//        StringBuilder stringBuilder;
//        stringBuilder.append(" ")
//    }
//    private void test() throws SQLException {
//
//        Statement statement=connection.createStatement();
//        String sql="SELECT * FROM adminsss";
//        ResultSet resultSet=statement.executeQuery(sql);
//
//        while (resultSet.next()){
//            System.out.println(resultSet.getString("login"));
//        }

//        while (resultSet.next()){
//            System.out.println(resultSet.getInt("login"));
//        }
}




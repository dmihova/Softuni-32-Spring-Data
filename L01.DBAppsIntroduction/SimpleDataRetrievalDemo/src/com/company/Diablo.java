package com.company;

import java.sql.*;
import java.util.Properties;

public class Diablo {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "admin");
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", props);

       String SQL = "SELECT u.user_name,u.first_name, u.last_name," +
                " (SELECT COUNT(*) FROM users_games WHERE user_id=u.id) AS game_count " +
                " FROM users AS u " +
                " WHERE u.user_name = ? ";

        String SQL1=   "SELECT  u.user_name, u.first_name, u.last_name, " +
                 " COUNT(ug.user_id) AS game_count " +
                " FROM users AS u JOIN  users_games  AS ug ON ug.user_id=u.id" +
                " WHERE u.user_name = ? GROUP BY ug.user_id";


        PreparedStatement preparedStatement =  connection.prepareStatement(SQL);
        preparedStatement.setString(1, "VGeorgiev");

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getString("first_name") + " " +
                    resultSet.getString("last_name") + " " +
                    resultSet.getInt("game_count")
            );
        } else {
            System.out.println("No such result");
        }

        connection.close();
    }
}

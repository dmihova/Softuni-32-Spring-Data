package com.company;

import java.sql.*;
import java.util.Properties;

public class Main_v1 {
    public static void main(String[] args) throws SQLException {

        String user = "root";
        String password = "admin";
        String salary= "10000.0";

        Properties properties =new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", properties);

       // connection.setAutoCommit(false);
        //connection.commit();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");
        preparedStatement.setDouble(1, Double.parseDouble(salary));
        ResultSet resultSet = preparedStatement.executeQuery();

        int count=0;
        while(resultSet.next()){
            count++;
            String middleName =   resultSet.getString("middle_name");
            System.out.printf("%d %s %s %s %.2f%n",count,
                    resultSet.getString("first_name") ,
                    (middleName==null) ? "" : middleName ,
                            resultSet.getString("last_name"),
                            resultSet.getDouble("salary")
            );
        }

        connection.close();
    }
}

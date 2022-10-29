package ORM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static Connection connection;
    private  static  final  String jdbc = "jdbc:mysql://localhost:3306/%s";

    public   static void  createConnection (String username, String password, String dbName) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user",username);
        properties.setProperty("password",password);
        String formattedJdbc = String.format(jdbc,dbName) ;
        connection = DriverManager.getConnection(formattedJdbc,properties);
    }

    public  static Connection getConnection(){
            return connection;
    }


}

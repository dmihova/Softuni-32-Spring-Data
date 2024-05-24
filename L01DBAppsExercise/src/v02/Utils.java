package v02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class Utils {
    static Connection getMySQLConnection() throws SQLException {
        final Properties properties = new Properties();
        properties.setProperty(v02.DBAccessSettings.USER_KEY, v02.DBAccessSettings.USER_VALUE);
        properties.setProperty(v02.DBAccessSettings.PASSWORD_KEY, v02.DBAccessSettings.PASSWORD_VALUE);
        return DriverManager.
                getConnection(v02.DBAccessSettings.URL_Connection, properties);

    }

}

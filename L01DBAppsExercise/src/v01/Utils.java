package v01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class Utils {
    static Connection getMySQLConnection() throws SQLException {
        final Properties properties = new Properties();
        properties.setProperty(DBAccessSettings.USER_KEY, DBAccessSettings.USER_VALUE);
        properties.setProperty(DBAccessSettings.PASSWORD_KEY, DBAccessSettings.PASSWORD_VALUE);
        return DriverManager.
                getConnection(DBAccessSettings.URL_Connection, properties);

    }

}

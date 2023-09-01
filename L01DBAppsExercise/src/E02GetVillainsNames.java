import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class E02GetVillainsNames {
    private final static String PRINT_FORMAT = "%s %d %n";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getMySQLConnection();
        PreparedStatement ps = connection.prepareStatement(DBQueries.GET_VILLAIN_NAME_WITH_MINIONS_MORE_THEN);
        ps.setInt(1, 15);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.printf(PRINT_FORMAT,
                    rs.getString(DBQueries.COLUMN_LABEL_VILLAIN_NAME),
                    rs.getInt(DBQueries.COLUMN_LABEL_MINION_COUNT)
            );
        }
        connection.close();
    }

}

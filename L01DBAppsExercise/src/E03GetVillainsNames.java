import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class E03GetVillainsNames {

    private final static String GET_VILLAIN_NAMES =
            "select v.name, COUNT(DISTINCT(mv.minion_id)) AS minion_count" +
                    " FROM  villains AS v LEFT JOIN minions_villains as mv on v.id=mv.villain_id" +
                    " GROUP BY mv.villain_id" +
                    " HAVING minion_count>?" +
                    " ORDER BY minion_count DESC";
    private final static String COLUMN_LABEL_NAME = "name";
    private final static String COLUMN_LABEL_COUNT = "minion_count";
    private final static String PRINT_FORMAT = "%s %d %n";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getMySQLConnection();
        final PreparedStatement stmt = connection.prepareStatement(GET_VILLAIN_NAMES);
        stmt.setInt(1, 15);

        final ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            System.out.printf(PRINT_FORMAT,
                    resultSet.getString(COLUMN_LABEL_NAME),
                    resultSet.getInt(COLUMN_LABEL_COUNT));
        }
        connection.close();
    }

}

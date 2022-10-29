import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class E02GetMinionNames {
    private final static String GET_VILLAIN_NAME = "SELECT name FROM villains WHERE id =?";
    private final static String COLUMN_LABEL_VILLAIN_NAME = "name";
    private final static String PRINT_VILLAIN_FORMAT = "Villain: %s %n";
    private final static String PRINT_NO_VILLAIN_FORMAT = "No villain with ID %d exists in the database.%n";

    private final static String GET_VILLAIN_MINIONS =
            "select m.name, m.age" +
                    " FROM  minions AS m JOIN minions_villains as mv on m.id=mv.minion_id" +
                    " WHERE mv.villain_id=?";
    private final static String COLUMN_LABEL_MINION_NAME = "name";
    private final static String COLUMN_LABEL_MINION_AGE = "age";
    private final static String PRINT_MINION = "%d. %s %d%n";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getMySQLConnection();
        Scanner scanner = new Scanner(System.in);
        final int villainId =Integer.parseInt(scanner.nextLine());

        final PreparedStatement statementGetVillain = connection.prepareStatement(GET_VILLAIN_NAME);
        statementGetVillain.setInt(1, villainId);
        final ResultSet resultSet = statementGetVillain.executeQuery();

        if (!resultSet.next()) {
            System.out.printf(PRINT_NO_VILLAIN_FORMAT, villainId);
            connection.close();
            return;
        }

        System.out.printf(PRINT_VILLAIN_FORMAT, resultSet.getString(COLUMN_LABEL_VILLAIN_NAME));

        final PreparedStatement statementGetMinions = connection.prepareStatement(GET_VILLAIN_MINIONS);
        statementGetMinions.setInt(1, villainId);
        final ResultSet resultSetMinions = statementGetMinions.executeQuery();
        int count = 0;
        while (resultSetMinions.next()) {
            count++;
            System.out.printf(PRINT_MINION, count,
                    resultSetMinions.getString(COLUMN_LABEL_MINION_NAME),
                    resultSetMinions.getInt(COLUMN_LABEL_MINION_AGE)
            );
        }
        connection.close();
    }
}

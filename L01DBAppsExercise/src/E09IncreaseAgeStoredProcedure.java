import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class E09IncreaseAgeStoredProcedure {
    private final static String GET_MINION_BY_ID = "SELECT `name`,age FROM minions WHERE id=?";
    private final static String PROC_UPDATE_MINION_AGE_BY_ID = "{CALL usp_get_older(?)}";

    private final static String COLUMN_LABEL_MINION_NAME = "name";
    private final static String COLUMN_LABEL_MINION_AGE = "age";

    private final static String REQUEST_MINION_ID = "Input minion id:";
    private static final String NO_MINION_WITH_ID_EXISTS = "No minion with ID %d exists.%n";
    private static final String PRINT_FORMAT_MINION_INFO = "%s %d%n";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.print(REQUEST_MINION_ID);
        int minionId = Integer.parseInt(scanner.nextLine());

        final Connection connection = Utils.getMySQLConnection();

        PreparedStatement statementProcUpdateMinion = connection.prepareCall(PROC_UPDATE_MINION_AGE_BY_ID);
        statementProcUpdateMinion.setInt(1, minionId);
        statementProcUpdateMinion.executeUpdate();

        PreparedStatement statementGetMinion = connection.prepareStatement(GET_MINION_BY_ID);
        statementGetMinion.setInt(1, minionId);
        ResultSet resultGetMinion = statementGetMinion.executeQuery();

        if (!resultGetMinion.next()) {
            System.out.printf(NO_MINION_WITH_ID_EXISTS, minionId);

        } else {
            System.out.printf(PRINT_FORMAT_MINION_INFO,
                    resultGetMinion.getString(COLUMN_LABEL_MINION_NAME),
                    resultGetMinion.getInt(COLUMN_LABEL_MINION_AGE));
        }
        connection.close();
    }
}


/*
DELIMITER $$
CREATE PROCEDURE  usp_get_older (minion_id INT)
BEGIN
    UPDATE minions  SET age = age + 1   WHERE id = minion_id;
END
$$
DELIMITER ;
*/

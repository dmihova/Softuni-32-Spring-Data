import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class E06RemoveVillain {

    private final static String PRINT_FORMAT_NOT_SUCH_VILLAIN = "No such villain was found";
    private final static String PRINT_FORMAT_VILLAIN_DELETED = "%s was deleted%n";
    private final static String PRINT_FORMAT_MINION_RELEASED = "%d minions released%n";


    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getMySQLConnection();
        Scanner scanner = new Scanner(System.in);
        final int villainId = Integer.parseInt(scanner.nextLine());

        final PreparedStatement statementGetVillain = connection.prepareStatement(DBQueries.GET_VILLAIN_NAME_BY_ID);
        statementGetVillain.setInt(1, villainId);
        ResultSet resultSetGetVillain = statementGetVillain.executeQuery();

        if (!resultSetGetVillain.next()) {
            System.out.print(PRINT_FORMAT_NOT_SUCH_VILLAIN);
            connection.close();
            return;
        }

        final String villainName = resultSetGetVillain.getString(DBQueries.COLUMN_LABEL_VILLAIN_NAME);
        final PreparedStatement statementGetMinionsCount = connection.prepareStatement(DBQueries.GET_COUNT_MINIONS_BY_VILLAIN);
        statementGetMinionsCount.setInt(1, villainId);
        ResultSet resultSetGetCountMinions = statementGetMinionsCount.executeQuery();
        resultSetGetCountMinions.next();
        int countOfMinions = resultSetGetCountMinions.getInt(DBQueries.COLUMN_LABEL_MINION_COUNT );
        connection.setAutoCommit(false);
        try (
             final PreparedStatement statementDeleteMinionsForVillain = connection.prepareStatement(DBQueries.DELETE_MINIONS_BY_VILLAIN);
             final PreparedStatement statementDeleteVillainById = connection.prepareStatement(DBQueries.DELETE_VILLAIN_BY_ID);
        ) {
            statementDeleteMinionsForVillain.setInt(1, villainId);
            statementDeleteMinionsForVillain.executeUpdate();
            statementDeleteVillainById.setInt(1, villainId);
            statementDeleteVillainById.executeUpdate();
            connection.commit();
            System.out.printf(PRINT_FORMAT_VILLAIN_DELETED, villainName);
            System.out.printf(PRINT_FORMAT_MINION_RELEASED, countOfMinions);
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }

        connection.close();
    }
}

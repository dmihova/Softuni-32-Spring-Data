package v01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class E03GetMinionNames {
    private final static String PRINT_VILLAIN_FORMAT = "Villain: %s\n";
    private final static String PRINT_NO_VILLAIN_FORMAT = "No villain with ID %d exists in the database.%n";
    private final static String PRINT_MINION = "%d. %s %d\n";


    public static void main(String[] args) throws SQLException {

        Connection connection = Utils.getMySQLConnection();

        Scanner scanner = new Scanner(System.in);
        final int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement psGetVillain = connection.prepareStatement(DBQueries.GET_MINION_NAME_AGE_BY_VILLAINID);
        psGetVillain.setInt(1, villainId);
        ResultSet rsVillain = psGetVillain.executeQuery();

        if (!rsVillain.next()) {
            System.out.printf(PRINT_NO_VILLAIN_FORMAT, villainId);
            return;

        }

        System.out.printf(PRINT_VILLAIN_FORMAT, rsVillain.getString(DBQueries.COLUMN_LABEL_VILLAIN_NAME));
        PreparedStatement ps = connection.prepareStatement(DBQueries.GET_MINION_NAME_AGE_BY_VILLAINID);
        ps.setInt(1, villainId);
        ResultSet rsMinions = ps.executeQuery();


        int count = 0;
        while (rsMinions.next()) {
            count++;
            System.out.printf(PRINT_MINION, count,
                    rsMinions.getString(DBQueries.COLUMN_LABEL_MINION_NAME),
                    rsMinions.getInt(DBQueries.COLUMN_LABEL_MINION_AGE));
        }


        connection.close();
    }

}

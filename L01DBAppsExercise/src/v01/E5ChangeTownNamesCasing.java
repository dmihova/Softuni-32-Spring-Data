package v01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class E5ChangeTownNamesCasing {
    private final static String PRINT_FORMAT_AFFECTED= "%d town names were affected.%n" ;
    private final static String PRINT_FORMAT_NOT_AFFECTED="No town names were affected.%n";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getMySQLConnection();
        Scanner scanner = new Scanner(System.in);
        final String country = scanner.nextLine();

        final PreparedStatement statementUpdateTownName = connection.prepareStatement(DBQueries.UPDATE_TOWN_NAMES_BY_COUNTRY);
        statementUpdateTownName.setString(1, country);
        final int updatedCount =statementUpdateTownName.executeUpdate();
        if (updatedCount==0){
            System.out.printf(PRINT_FORMAT_NOT_AFFECTED);
            connection.close();
            return;
        }

        final PreparedStatement statementGetTowns = connection.prepareStatement(DBQueries.GET_TOWN_NAMES_BY_COUNTRY);
        statementGetTowns.setString(1, country);
        ResultSet resultSetGetTownsByCountry = statementGetTowns.executeQuery();

        ArrayList<String> towns = new ArrayList<>();
        while (resultSetGetTownsByCountry.next()){
            towns.add(resultSetGetTownsByCountry.getString(DBQueries.COLUMN_LABEL_TOWN_NAME));
        }
        System.out.printf(PRINT_FORMAT_AFFECTED,towns.size() );
        System.out.println(towns );

        connection.close();
    }
}



package v02;

import v01.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class E5ChangeTownNamesCasing {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        final String countryName = scanner.nextLine();
        Connection connection = Utils.getMySQLConnection();

        String sqlUpdate =  "UPDATE towns SET name =upper(name) WHERE country =?";
        PreparedStatement psUpdate = connection.prepareStatement(sqlUpdate);
        psUpdate.setString(1, countryName);
        int updatedCount =   psUpdate.executeUpdate( );
        if (updatedCount==0){
            System.out.printf("No town names were affected.%n");
            connection.close();
            return;
        }

        final PreparedStatement psSelect = connection.prepareStatement( "SELECT name FROM towns WHERE country =?");
        psSelect.setString(1, countryName);
        ResultSet rs = psSelect.executeQuery();

        ArrayList<String> towns = new ArrayList<>();
        while (rs.next()){
            towns.add(rs.getString(DBQueries.COLUMN_LABEL_TOWN_NAME));
        }
        System.out.printf( "%d town names were affected.%n",towns.size() );
        System.out.println(towns );

        connection.close();

    }
}

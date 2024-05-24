package v02;


import v01.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class E06RemoveVillain {
    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getMySQLConnection();
        Scanner scanner = new Scanner(System.in);
        final int villainId = Integer.parseInt(scanner.nextLine());

        final PreparedStatement psSelectVillain = connection.prepareStatement("SELECT name FROM villains WHERE id =?");
        psSelectVillain.setInt(1, villainId);
        ResultSet resultSetGetVillain = psSelectVillain.executeQuery();

        if (!resultSetGetVillain.next()) {
            System.out.print("No such villain was found");
            connection.close();
            return;
        }

        final String villainName = resultSetGetVillain.getString("name");
        String sqlCountMinions = "SELECT COUNT( minion_id) AS minion_count FROM minions_villains WHERE villain_id =?";
        final PreparedStatement statementGetMinionsCount = connection.prepareStatement(sqlCountMinions);
        statementGetMinionsCount.setInt(1, villainId);
        ResultSet resultSetGetCountMinions = statementGetMinionsCount.executeQuery();
        resultSetGetCountMinions.next();
        int countOfMinions = resultSetGetCountMinions.getInt("minion_count");

        connection.setAutoCommit(false);

        try (
                final PreparedStatement statementDeleteMinionsForVillain = connection.prepareStatement("DELETE FROM minions_villains WHERE villain_id =?");
                final PreparedStatement statementDeleteVillainById = connection.prepareStatement("DELETE  FROM villains WHERE id =?");
       ) {
            statementDeleteMinionsForVillain.setInt(1, villainId);
            statementDeleteVillainById.setInt(1, villainId);
            statementDeleteMinionsForVillain.executeUpdate();
            statementDeleteVillainById.executeUpdate();
            connection.commit();
            System.out.printf("%s was deleted%n", villainName);
            System.out.printf("%d minions released%n", countOfMinions);

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }


        connection.close();

    }
}

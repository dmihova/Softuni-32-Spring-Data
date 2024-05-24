package v02;

import v01.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class E08IncreaseMinionsAge {
      public static void main(String[] args) throws SQLException {
        final Connection connection =  Utils.getMySQLConnection();
        Scanner scanner = new Scanner(System.in);
        List<Integer> minionsIdList = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(v -> Integer.parseInt(v))
                .collect(Collectors.toList());

        String placeHolders = minionsIdList.stream().map(v -> "?").collect(Collectors.joining(", "));

        String updateMinionsByIdList = String.format("UPDATE minions SET age = age + 1, `name` = lower(`name`) WHERE id IN (%s)", placeHolders);
        PreparedStatement statementUpdateMinions = connection.prepareStatement(updateMinionsByIdList);
        for (int i = 1; i <= minionsIdList.size(); i++) {
            statementUpdateMinions.setInt(i, minionsIdList.get(i - 1));
        }
        statementUpdateMinions.executeUpdate();

        PreparedStatement statementGetMinions = connection.prepareStatement( "SELECT name, age FROM minions ORDER BY id");
        ResultSet resultSetMinions = statementGetMinions.executeQuery();
        while (resultSetMinions.next()) {
            System.out.printf("%s %d%n",
                    resultSetMinions.getString("name"),
                    resultSetMinions.getInt("age"));
        }

        connection.close();
    }
}

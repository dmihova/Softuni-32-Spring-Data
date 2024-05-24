package v01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class E08IncreaseMinionsAge {
    static final String UPDATE_MINIONS_BY_ID = "UPDATE minions SET age = age + 1, `name` = lower(`name`) WHERE id IN (%s)";
    static final String GET_ALL_MINIONS = "SELECT name, age FROM minions ORDER BY id";

    private final static String PRINT_FORMAT_MINION = "%s %d%n";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getMySQLConnection();
        Scanner scanner = new Scanner(System.in);
        List<Integer> minionsIdList = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(v -> Integer.parseInt(v))
                .collect(Collectors.toList());

        String placeHolders = minionsIdList.stream().map(v -> "?").collect(Collectors.joining(", "));
        String updateMinionsByIdList = String.format(UPDATE_MINIONS_BY_ID, placeHolders);
        PreparedStatement statementUpdateMinions = connection.prepareStatement(updateMinionsByIdList);
        for (int i = 1; i <= minionsIdList.size(); i++) {
            statementUpdateMinions.setInt(i, minionsIdList.get(i - 1));
        }
        statementUpdateMinions.executeUpdate();

        PreparedStatement statementGetMinions = connection.prepareStatement(GET_ALL_MINIONS);
        ResultSet resultSetMinions = statementGetMinions.executeQuery();
        while (resultSetMinions.next()) {
            System.out.printf(PRINT_FORMAT_MINION,
                    resultSetMinions.getString(DBQueries.COLUMN_LABEL_MINION_NAME),
                    resultSetMinions.getInt(DBQueries.COLUMN_LABEL_MINION_AGE));
        }

        connection.close();
    }
}

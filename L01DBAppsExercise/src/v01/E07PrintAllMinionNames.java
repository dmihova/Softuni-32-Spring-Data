package v01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class E07PrintAllMinionNames {



    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getMySQLConnection();

        PreparedStatement getAllMinions = connection.prepareStatement( DBQueries.GET_ALL_MINIONS_NAME);
        ResultSet resultSetMinions = getAllMinions.executeQuery();

        ArrayDeque<String> minionsNameQueue = new ArrayDeque<>();
        while (resultSetMinions.next()) {
            minionsNameQueue.offer(resultSetMinions.getString(DBQueries.COLUMN_LABEL_MINION_NAME));
        }
        connection.close();

        while (minionsNameQueue.size() > 2) {
            System.out.println(minionsNameQueue.pollFirst());
            System.out.println(minionsNameQueue.pollLast());
        }

        while(!minionsNameQueue.isEmpty()) {
            System.out.println(minionsNameQueue.poll());
        }


    }
}

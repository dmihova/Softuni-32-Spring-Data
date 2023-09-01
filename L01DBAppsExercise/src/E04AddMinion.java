import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class E04AddMinion {
    private final static String PRINT_TOWN_ADDED = "Town %s was added to the database.%n";
    private final static String PRINT_VILLAIN_ADDED = "Villain %s was added to the database.%n";
    private final static String PRINT_MINION_ADDED_FOR_VILLAIN ="Successfully added %s to be minion of %s%n";

    public static void main(String[] args) throws SQLException {

        Connection connection = Utils.getMySQLConnection();

        Scanner scanner = new Scanner(System.in);
        String [] minionArr = scanner.nextLine().split("\\s+");  // Minion: Robert 14 Berlin
        final String villainName = scanner.nextLine().split("\\s+")[1]; //Villain: Gru
        final String minionName =minionArr[1];
        final String town =minionArr[3];
        final int minionAge =Integer.parseInt(minionArr[2]);

        final int townId = getOrInsertTownId(connection,town);
        final int villainId= getOrInsertVillainId(connection,villainName);
        final int minionId = createMinion(connection,minionName,minionAge,townId);
        addMinionToVillain(connection,villainId,minionId);
        System.out.printf(PRINT_MINION_ADDED_FOR_VILLAIN,minionName,villainName);
        connection.close();
    }

    private static void addMinionToVillain(Connection connection, int villainId, int minionId) throws SQLException {
        final PreparedStatement psInsert = connection.prepareStatement(DBQueries.INSERT_MINION_VILLAIN);
        psInsert.setInt(1,minionId);
        psInsert.setInt(2,villainId);
    }

    private static int createMinion(Connection connection, String name, int age, int townId) throws SQLException {
        final PreparedStatement psInsert = connection.prepareStatement(DBQueries.INSERT_MINION_NAME_AGE_TOWN);
        psInsert.setString(1,name);
        psInsert.setInt(2,age);
        psInsert.setInt(3,townId);
        psInsert.executeUpdate();
        final PreparedStatement ps = connection.prepareStatement(DBQueries.GET_LAST_MINION);
        ResultSet rs =ps.executeQuery();
        rs.next();
        return rs.getInt(DBQueries.COLUMN_LABEL_MINION_ID);

    }

    private static int getOrInsertVillainId(Connection connection, String name) throws SQLException {
        final  PreparedStatement ps = connection.prepareStatement(DBQueries.GET_VILLAIN_ID_BY_NAME);
        ps.setString(1,name);
        ResultSet rs =ps.executeQuery();
        if (rs.next()){
            return rs.getInt(DBQueries.COLUMN_LABEL_VILLAIN_ID);
        }

        final  PreparedStatement psInsert = connection.prepareStatement(DBQueries.INSERT_VILLAIN_NAME_EVIL);
        psInsert.setString(1,name);
        psInsert.setString(2,"evil");
        psInsert.executeUpdate();
        System.out.printf(PRINT_VILLAIN_ADDED,name);

        rs =ps.executeQuery();
        rs.next();
        return  rs.getInt(DBQueries.COLUMN_LABEL_VILLAIN_ID);

    }

    private static int getOrInsertTownId(Connection connection, String town) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DBQueries.GET_TOWN_ID_BY_NAME);
        ps.setString(1,town);
        ResultSet rs =ps.executeQuery();
        if (rs.next()){
            return rs.getInt(DBQueries.COLUMN_LABEL_TOWN_ID);
        }
        PreparedStatement psInsert = connection.prepareStatement(DBQueries.INSERT_TOWN_NAME);
        psInsert.setString(1,town);
        int res = psInsert.executeUpdate();
        System.out.printf(PRINT_TOWN_ADDED,town);

        rs =ps.executeQuery();
        rs.next();
        return  rs.getInt(DBQueries.COLUMN_LABEL_TOWN_ID);
    }
}

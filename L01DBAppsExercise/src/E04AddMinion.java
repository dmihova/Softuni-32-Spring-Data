import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class E04AddMinion {

    private final static String GET_TOWN_BY_NAME = "SELECT id FROM towns WHERE name =?";
    private final static String INSERT_TOWN = "INSERT INTO towns (name) VALUES(?)";
    private final static String COLUMN_LABEL_TOWN_ID = "id";
    private final static String TOWN_ADDED = "Town %s was added to the database.%n";

    private final static String GET_VILLAIN_BY_NAME = "SELECT id FROM villains WHERE name =? ";
    private final static String INSERT_VILLAIN = "INSERT INTO villains (name,evilness_factor) VALUES(?,'evil')";
    private final static String COLUMN_LABEL_VILLAIN_ID = "id";
    private final static String VILLAIN_ADDED = "Villain %s was added to the database.%n";

    private final static String GET_LAST_MINION = "SELECT id FROM minions ORDER BY id DESC LIMIT 1";
    private final static String INSERT_MINION = "INSERT INTO minions(name,age,town_id) VALUES(?,?,?)";
    private final static String COLUMN_LABEL_MINION_ID = "id";

    private final static String INSERT_MINION_VILLAIN = "INSERT INTO minions_villains(minion_id,villain_id) VALUES(?,?)";
    private final static String MINION_ADDED_FOR_VILLAIN="Successfully added %s to be minion of %s%n";

    public static void main(String[] args)  throws SQLException {
        final Connection connection = Utils.getMySQLConnection();
        Scanner scanner = new Scanner(System.in);
        final String [] minionInfo =  scanner.nextLine() .split(" ");
        final String minionName= minionInfo[1];
        final int minionAge = Integer.parseInt(minionInfo[2]);
        final String minionTown =  minionInfo[3];;
        final String villainName=  scanner.nextLine() .split(" ")[1];

     /*   final String villainName= "Gru";
        final String minionName = "Robert";
        final int minionAge = 14;
        final String minionTown = "Berlin";*/

        int townId = get_town_id(connection,minionTown);
        int villainId = get_villain_id(connection,villainName);
        int minionId = create_minion(connection,minionName,minionAge,townId);
        add_minion_to_villain(connection,minionId,villainId);
        System.out.printf(MINION_ADDED_FOR_VILLAIN,minionName,villainName);
        connection.close();
      }

    private static void add_minion_to_villain(Connection connection, int minionId, int villainId) throws SQLException {
        final PreparedStatement statementInsertMinionVillains =  connection.prepareStatement(INSERT_MINION_VILLAIN);
        statementInsertMinionVillains.setInt(1, minionId);
        statementInsertMinionVillains.setInt(2, villainId);
        statementInsertMinionVillains.executeUpdate();

    }

    private static int create_minion(Connection connection, String minionName, int minionAge, int townId) throws SQLException {



        final PreparedStatement statementInsertMinion =  connection.prepareStatement(INSERT_MINION);
        statementInsertMinion.setString(1, minionName);
        statementInsertMinion.setInt(2, minionAge);
        statementInsertMinion.setInt(3, townId);
        statementInsertMinion.executeUpdate();


        final PreparedStatement statementGetMinion = connection.prepareStatement(GET_LAST_MINION);
        ResultSet resultSetGetMinion = statementGetMinion.executeQuery();
        resultSetGetMinion.next() ;
        return resultSetGetMinion.getInt(COLUMN_LABEL_MINION_ID);
    }

    private static int get_villain_id(Connection connection, String villainName) throws SQLException {
        final PreparedStatement statementGetVillain = connection.prepareStatement(GET_VILLAIN_BY_NAME);
        statementGetVillain.setString(1, villainName);
        ResultSet resultSetGetVillain = statementGetVillain.executeQuery();

        if (resultSetGetVillain.next()) {
            return resultSetGetVillain.getInt(COLUMN_LABEL_VILLAIN_ID);
        }
        final PreparedStatement statementInsertVillain =  connection.prepareStatement(INSERT_VILLAIN);
        statementInsertVillain.setString(1, villainName);
        statementInsertVillain.executeUpdate();
        System.out.printf(VILLAIN_ADDED,villainName);
        resultSetGetVillain = statementGetVillain.executeQuery();
        resultSetGetVillain.next() ;
        return resultSetGetVillain.getInt(COLUMN_LABEL_VILLAIN_ID);

    }

    private static int get_town_id(Connection connection, String minionTown) throws SQLException {
        final PreparedStatement statementGetTown = connection.prepareStatement(GET_TOWN_BY_NAME);
        statementGetTown.setString(1, minionTown);
        ResultSet resultSetGetTown = statementGetTown.executeQuery();

        if (resultSetGetTown.next()) {
            return resultSetGetTown.getInt(COLUMN_LABEL_TOWN_ID);
        }
        final PreparedStatement statementInsertTown =  connection.prepareStatement(INSERT_TOWN);
        statementInsertTown.setString(1, minionTown);
        statementInsertTown.executeUpdate();
        System.out.printf(TOWN_ADDED,minionTown);

        resultSetGetTown = statementGetTown.executeQuery();
        resultSetGetTown.next() ;
        return resultSetGetTown.getInt(COLUMN_LABEL_TOWN_ID);
    }
}

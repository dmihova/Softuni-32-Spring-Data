package v02;

import v01.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class E04AddMinion {
    static final String PRINT_TOWN_ADDED = "Town %s was added to the database.";
    private final static String PRINT_VILLAIN_ADDED = "Villain %s was added to the database.%n";
    private final static String PRINT_MINION_ADDED_FOR_VILLAIN ="Successfully added %s to be minion of %s%n";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        String [] minionArr = scanner.nextLine().split("\\s+");  // Minion: Robert 14 Berlin
        final String villainName = scanner.nextLine().split("\\s+")[1]; //Villain: Gru
        final String minionName =minionArr[1];
        final String town =minionArr[3];
        final int minionAge =Integer.parseInt(minionArr[2]);

        Connection connection = Utils.getMySQLConnection();
        int townId = getTownId(connection, town);
        int villainId = getVillainId(connection, villainName);
        int minionId = getMinionId(connection,minionName,townId,minionAge);
        addMinionVillain(connection,villainId,minionId);
        System.out.printf(PRINT_MINION_ADDED_FOR_VILLAIN, minionName,villainName);


    }

    private static void addMinionVillain(Connection connection,int villainId, int minionId) throws SQLException {
        String sqlGet = "SELECT minion_id FROM minions_villains   WHERE minion_id= ? AND villain_id= ?";
        PreparedStatement psSelect = connection.prepareStatement(sqlGet);
        psSelect.setInt(1, minionId);
        psSelect.setInt(2, villainId);
        ResultSet rs = psSelect.executeQuery();
        if (rs.next()) {
            return  ;
        }
        String sqlInsert = "INSERT INTO minions_villains (minion_id,villain_id) VALUES(?,?)";
        PreparedStatement psInsert = connection.prepareStatement(sqlInsert);
        psInsert.setInt(1, minionId);
        psInsert.setInt(2, villainId);
        int res = psInsert.executeUpdate();

    }

    private static int getMinionId(Connection connection, String minionName, int townId, int minionAge) throws SQLException {
        String sqlGet = "SELECT id FROM minions   WHERE name= ? AND town_id= ? AND age = ?";
        PreparedStatement psSelect = connection.prepareStatement(sqlGet);
        psSelect.setString(1, minionName);
        psSelect.setInt(2, townId);
        psSelect.setInt(3, minionAge);
        ResultSet rs = psSelect.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }

        String sqlInsert = "INSERT INTO minions (name,town_id,age) VALUES(?,?,?)";
        PreparedStatement psInsert = connection.prepareStatement(sqlInsert);
        psInsert.setString(1, minionName);
        psInsert.setInt(2, townId);
        psInsert.setInt(3, minionAge);
        int res = psInsert.executeUpdate();
        rs = psSelect.executeQuery();
        rs.next();
        return  rs.getInt("id");
    }

    private static int getVillainId(Connection connection, String villainName) throws SQLException {
        String sqlSelect = "SELECT id FROM villains  WHERE name= ?";
        PreparedStatement psSelect = connection.prepareStatement(sqlSelect);
        psSelect.setString(1, villainName);
        ResultSet rs = psSelect.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
        String sqlInsert = "INSERT INTO villains (name,evilness_factor) VALUES(?,?)";
        PreparedStatement psInsert = connection.prepareStatement(sqlInsert);
        psInsert.setString(1, villainName);
        psInsert.setString(2, "evil");
        int res = psInsert.executeUpdate();
        System.out.printf(PRINT_VILLAIN_ADDED, villainName);
        rs = psSelect.executeQuery();
        rs.next();
        return rs.getInt("id");
    }

    private static int getTownId(Connection connection, String town) throws SQLException {
        String sqlSelect = "SELECT id FROM towns   WHERE name= ?";
        PreparedStatement psSelect = connection.prepareStatement(sqlSelect);
        psSelect.setString(1, town);
        ResultSet rs = psSelect.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }

        String insertTown = "INSERT INTO towns (name) VALUES(?)";
        PreparedStatement psInsert = connection.prepareStatement(insertTown);
        psInsert.setString(1, town);
        int res = psInsert.executeUpdate();
        System.out.printf(PRINT_TOWN_ADDED, town);
        rs = psSelect.executeQuery();
        rs.next();
        return rs.getInt("id");
    }

}



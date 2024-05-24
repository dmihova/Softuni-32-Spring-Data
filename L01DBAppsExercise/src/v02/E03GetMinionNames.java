package v02;

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
        Connection connection=  Utils.getMySQLConnection();
        Scanner scanner = new Scanner(System.in);
        final int id = Integer.parseInt(scanner.nextLine());

        String sql ="SELECT name FROM villains   WHERE id= ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            System.out.printf(PRINT_VILLAIN_FORMAT,rs.getString("name"));
                printVillainMinions(connection,id);
        }else{
            System.out.printf(PRINT_NO_VILLAIN_FORMAT,id);
        }

    }

    private static void printVillainMinions( Connection connection,int id) throws SQLException {
        String sql ="SELECT DISTINCT m.name,m.age FROM  minions m join minions_villains mv WHERE m.id=mv.minion_id AND mv.villain_id= ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);
        ResultSet rs = statement.executeQuery();
        int count=0;
        while(rs.next()){
            count++;
            System.out.printf(PRINT_MINION,count,rs.getString("name"),rs.getInt("age"));
        }

    }
}

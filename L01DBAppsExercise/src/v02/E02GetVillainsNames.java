package v02;

import java.sql.*;

public class E02GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Connection connection=  Utils.getMySQLConnection();
        String sql ="SELECT v.name,COUNT(DISTINCT(m.minion_id)) as minion_count FROM  villains v join minions_villains m "+
                    " on v.id= m.villain_id  GROUP BY v.id  HAVING minion_count>15 ORDER BY minion_count DESC";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
          System.out.println(rs.getString("name")+" "+rs.getInt("minion_count"));
        }
    }
}

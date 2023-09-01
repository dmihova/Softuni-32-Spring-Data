public class DBQueries {
    public static final String GET_VILLAIN_NAME_BY_ID = "SELECT name FROM villains WHERE id =?";
    public static final String GET_VILLAIN_ID_BY_NAME = "SELECT id FROM villains WHERE name =?";
    public static final String GET_MINION_ID_BY_NAME_AGE ="SELECT id FROM minions WHERE name =? AND age=?";
    public static final   String GET_ALL_MINIONS_NAME = "SELECT name FROM minions";
    public static final String GET_LAST_MINION = "SELECT id FROM minions ORDER BY id DESC LIMIT 1";

    public static final String GET_MINION_NAME_AGE_BY_VILLAINID =
            "SELECT m.name, m.age" +
                    " FROM  minions AS m JOIN minions_villains as mv on m.id=mv.minion_id" +
                    " WHERE mv.villain_id=?";

    public static final String GET_VILLAIN_NAME_WITH_MINIONS_MORE_THEN =
            "SELECT v.name ,COUNT(DISTINCT mv.minion_id ) as minion_count"+
            " FROM villains AS v JOIN minions_villains as mv ON v.id=mv.villain_id "+
            " GROUP BY v.id HAVING minion_count>? "+
            " ORDER BY minion_count DESC";

    public final static String GET_COUNT_MINIONS_BY_VILLAIN =
            "SELECT COUNT( minion_id) AS minion_count FROM minions_villains WHERE villain_id =?";



      public static final String INSERT_VILLAIN_NAME_EVIL = "INSERT INTO villains (name,evilness_factor) VALUES(?,?)";
    public static final String INSERT_MINION_NAME_AGE_TOWN = "INSERT INTO minions (name,age,town_id) VALUES(?,?,?)";
    public static final  String INSERT_MINION_VILLAIN = "INSERT INTO minions_villains(minion_id,villain_id) VALUES(?,?)";


    public static final String GET_TOWN_ID_BY_NAME =  "SELECT id FROM towns WHERE name=?";
    public final static String GET_TOWN_NAMES_BY_COUNTRY = "SELECT name FROM towns WHERE country =?";
    public static final String INSERT_TOWN_NAME =  "INSERT INTO towns (name) VALUES(?)";
    public final static String UPDATE_TOWN_NAMES_BY_COUNTRY = "UPDATE towns SET name =upper(name) WHERE country =?";



    public final static String DELETE_MINIONS_BY_VILLAIN = "DELETE FROM minions_villains WHERE villain_id =?";
    public final static String DELETE_VILLAIN_BY_ID = "DELETE  FROM villains WHERE id =?";


    public static final String COLUMN_LABEL_MINION_NAME ="name";
    public static final String COLUMN_LABEL_MINION_AGE="age";
    public static final String COLUMN_LABEL_MINION_COUNT="minion_count";


    public static final String COLUMN_LABEL_VILLAIN_NAME="name";
    public static final String COLUMN_LABEL_VILLAIN_ID="id";

    public static final String COLUMN_LABEL_TOWN_ID = "id";
    public static final String  COLUMN_LABEL_TOWN_NAME = "name";


    public static final String COLUMN_LABEL_MINION_ID = "id";


}

package ORM;

public class SQLConstants {

    public static final String CREATE_QUERY_FORMAT = "CREATE TABLE %s (id INT PRIMARY KEY AUTO_INCREMENT, %s );";
    public static final String ADD_COLUMN_FORMAT = "ADD COLUMN %s %s";
    public static final String DROP_COLUM_FORMAT = "DROP COLUMN %s";
    public static final String ALTER_TABLE_FORMAT = "ALTER TABLE %s ADD COLUMN (%s)";
    public static final String DELETE_RECORD_BY_CONDITION_FORMAT = "DELETE FROM %s WHERE %s = %s";

    public static final String GET_ALL_COLUMN_NAME_BY_TABLE_NAME =
            "SELECT `COLUMN_NAME` FROM `INFORMATION_SCHEMA`.`COLUMNS`" +
                    " WHERE `TABLE_SCHEMA`='"+DbUtils.DB_NAME+"' AND `COLUMN_NAME` != 'id' AND `TABLE_NAME` = ?";

    public static final String WHERE_KEY_WORD = "WHERE ";
    public static final String CREATE_VALUE_FORMAT = "%s %s";
    public static final String VARCHAR = "VARCHAR(45)";
    public static final String INT = "INT";
    public static final String DATE = "DATE";
}

package ORM;

import ORM.annotations.Column;
import ORM.annotations.Entity;
import ORM.annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EntityManager<E> implements DBContext<E> {
    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws SQLException, IllegalAccessException {
        String tableName = this.getTableName(entity.getClass());
        String fieldList = this.getDBFieldsWithoutIdentity(entity);
        String valueList = this.getValuesWithoutIdentity(entity);
        String sql = String.format("Insert INTO %s (%s) VALUES (%s)",
                tableName, fieldList, valueList);

        return this.connection.prepareStatement(sql).execute();
    }

    private String getValuesWithoutIdentity(E entity) throws IllegalAccessException {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        List<String> result = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            if (declaredField.getAnnotation(Column.class) == null) {
                continue;
            }
            declaredField.setAccessible(true);
            Object value = declaredField.get(entity);
            result.add("\"" + value.toString() + "\"");
        }
        return String.join(",", result);

    }

    private String getDBFieldsWithoutIdentity(E entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.getAnnotation(Column.class) != null)
                .map(f -> f.getAnnotation(Column.class).name())
                .collect(Collectors.joining(","));

    }

    private String getTableName(Class<?> classEntity) {
        Entity annotation = classEntity.getAnnotation(Entity.class);
        if (annotation == null) {
            throw new ORMException("Provided class has no entity annotation");
        }
        return annotation.name();
    }

    @Override
    public Iterable<E> find(Class<E> classEntity) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return find(classEntity, "");
    }

    @Override
    public Iterable<E> find(Class<E> classEntity, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = this.getTableName(classEntity);
        String sql = String.format("SELECT * FROM %s %s ", tableName,
                where.isEmpty() ? "" : " WHERE  " + where);

        ResultSet resultSet = this.connection.prepareStatement(sql).executeQuery();

        List<E> result = new ArrayList<>();
        E entity = this.createEntity(classEntity, resultSet);
        while (entity != null) {
            result.add(entity);
            entity = this.createEntity(classEntity, resultSet);
        }
        return result;
    }

    @Override
    public E find_first(Class<E> clazz) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return find_first(clazz, "");
    }

    @Override
    public E find_first(Class<E> classEntity, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String tableName = this.getTableName(classEntity);
        String sql = String.format("SELECT * FROM %s %s LIMIT 1", tableName,
                where.equals("") ? "" : " WHERE  " + where);

        ResultSet resultSet = this.connection.prepareStatement(sql).executeQuery();

        return this.createEntity(classEntity, resultSet);
    }

    @Override
    public void doCreate(Class<E> classEntity) throws SQLException {
        String tableName = this.getTableName(classEntity);
        String query = String.format(SQLConstants.CREATE_QUERY_FORMAT,
                tableName, getAllFieldsAndDataTypesWithoutId(classEntity));
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();

    }

    @Override
    public void doAlter(Class<E> classEntity) throws SQLException {
        String tableName = this.getTableName(classEntity);
        String  newFields =  getNewFields(classEntity);
        if (!newFields.isEmpty()){
            String query = String.format(SQLConstants.ALTER_TABLE_FORMAT,
                    tableName,newFields);
            connection.prepareStatement(query).executeUpdate();
        }

    }

    @Override
    public void doDelete(E entity) throws SQLException, IllegalAccessException {
        final String tableName = getTableName(entity.getClass());

        final Field idField = getIdColumn(entity.getClass());

        final String idName = getSQLColumName(idField);

        idField.setAccessible(true);
        final Object  idValue= idField.get(entity);

        final String deleteQuery = String.format(SQLConstants.DELETE_RECORD_BY_CONDITION_FORMAT,
                    tableName, idName, idValue);

        final PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
        deleteStatement.execute();
    }
    private Field getIdColumn(Class<?> classEntity) {
        return Arrays.stream(classEntity.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity missing an Id column"));
    }
    private String getNewFields(Class<E> classEntity) throws SQLException {
        String tableName = this.getTableName(classEntity);
        Set<String> oldFields = getAllFieldsFromTable(classEntity, tableName);

        return Arrays.stream(classEntity.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class) && !f.isAnnotationPresent(Id.class))
                .filter(f -> !oldFields.contains(getSQLColumName(f)))
                .map(f -> getNameAndDataTypeOfField(f))
                .collect(Collectors.joining(","));
    }

    private Set<String> getAllFieldsFromTable(Class<E> entity, String tableName) throws SQLException {
        Set<String> allFields = new HashSet<>();

        final PreparedStatement getAllFieldsStatement =
                connection.prepareStatement(SQLConstants.GET_ALL_COLUMN_NAME_BY_TABLE_NAME);
        getAllFieldsStatement.setString(1, tableName);
        final ResultSet resultSet = getAllFieldsStatement.executeQuery();

        while (resultSet.next()) {
            allFields.add(resultSet.getString(1));
        }

        return allFields;
    }

    private String getAllFieldsAndDataTypesWithoutId(Class<E> classEntity) {
        return Arrays.stream(classEntity.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class) && !f.isAnnotationPresent(Id.class))
                .map(f -> getNameAndDataTypeOfField(f))
                .collect(Collectors.joining(","));

    }

    private String getNameAndDataTypeOfField(Field field) {
        return String.format(SQLConstants.CREATE_VALUE_FORMAT,
                getSQLColumName(field), getSQLType(field.getType()));
    }

    private String getSQLColumName(Field field) {
        return field.getAnnotationsByType(Column.class)[0].name();
    }

    private String getSQLType(Class<?> type) {
        if (type == Integer.class || type == int.class) {
            return SQLConstants.INT;
        } else if (type == LocalDate.class) {
            return SQLConstants.DATE;
        }
        return SQLConstants.VARCHAR;
    }

    private E createEntity(Class<E> entityType, ResultSet resultSet) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        if (!resultSet.next()) {
            return null;
        }
        E entity = entityType.getDeclaredConstructor().newInstance();

        Field[] declaredFields = entity.getClass().getDeclaredFields();

        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(Column.class) &&
                    !declaredField.isAnnotationPresent(Id.class)
            ) {
                continue;
            }
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            String fieldName;
            if (columnAnnotation == null) {
                fieldName = declaredField.getName();
            } else {
                fieldName = declaredField.getAnnotation(Column.class).name();
            }
            String value = resultSet.getString(fieldName);
            this.fillData(entity, declaredField, value);

        }

        return entity;
    }

    private E fillData(E entity, Field declaredField, String value) throws IllegalAccessException {
        declaredField.setAccessible(true);
        if (declaredField.getType() == long.class || declaredField.getType() == Long.class) {
            declaredField.setLong(entity, Long.parseLong(value));
        } else if (declaredField.getType() == int.class || declaredField.getType() == Integer.class) {
            Integer temp =Integer.parseInt(value);
            declaredField.setInt(entity, 10);

        } else if (declaredField.getType() == LocalDate.class) {
            declaredField.set(entity, LocalDate.parse(value));
        } else if (declaredField.getType() == String.class) {
            declaredField.set(entity, value);
        } else {
            throw new ORMException("Unsupported type " + declaredField.getType());
        }
        return entity;
    }


}

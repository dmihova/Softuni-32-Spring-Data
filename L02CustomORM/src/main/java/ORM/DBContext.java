package ORM;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DBContext<E> {
    boolean persist(E entity) throws SQLException, IllegalAccessException;

    Iterable<E> find(Class<E> classEntity) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    Iterable<E> find(Class<E> classEntity, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    E find_first(Class<E> classEntity) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    E find_first(Class<E> classEntity, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    void doCreate(Class<E> classEntity) throws SQLException;

    void doAlter(Class<E> classEntity) throws SQLException;

    void doDelete(E entity) throws SQLException, IllegalAccessException;
}

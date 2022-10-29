import Entities.Account;
import ORM.Connector;
import ORM.DbUtils;
import ORM.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Connector.createConnection("root","admin", DbUtils.DB_NAME);
        Connection connection = Connector.getConnection();

    //    EntityManager<User> userManager = new EntityManager<>(connection);
     //   User user  = new User("FUser4",28, LocalDate.now());
     //   userManager.persist(user);
     //    User user2 =  userManager.find_first(User.class);
     //   userManager.find (User.class,"age>1").forEach(u->System.out.println(u));
       EntityManager<Account> accountEntityManager = new EntityManager<>(connection);
       //accountEntityManager.doCreate(Account.class);
      // accountEntityManager.doAlter(Account.class);
       // Account account  = new Account("A2", 5, LocalDate.now(),"AAA2");
        //accountEntityManager.persist(account);
        accountEntityManager.find (Account.class,"").forEach(u->System.out.println(u));
    }
}

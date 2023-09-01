import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("l04-university-db");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
//        Product p = new Product("T1",12.4, BigDecimal.valueOf(2.2));
//        entityManager.persist(p);
//        Customer c = new Customer("T1","asfsafa","aaafasfsf");
//        entityManager.persist(c);
//        StoreLocation sl = new StoreLocation("locname1");
//        entityManager.persist(sl);


        //      Sale s = new Sale(p,c,sl, new Date());
        //     entityManager.persist(s);

        //    Product p = entityManager.find(Product.class,1);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}

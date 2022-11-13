import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("l04-hospital-db");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}

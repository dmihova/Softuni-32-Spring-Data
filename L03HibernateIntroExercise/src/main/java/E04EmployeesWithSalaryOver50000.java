import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class E04EmployeesWithSalaryOver50000 {
    private static String DATABASE_NANE = "soft_uni";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_NANE);
        final EntityManager entityManager = emf.createEntityManager();

        String query = "SELECT e.firstName FROM Employee e WHERE e.salary > 50000";
        entityManager.
                createQuery(query, String.class).
                getResultList().
                forEach(e -> System.out.println(e));

        entityManager.close();
    }
}

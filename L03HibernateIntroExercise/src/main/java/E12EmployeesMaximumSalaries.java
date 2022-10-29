import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class E12EmployeesMaximumSalaries {
    private static String DATABASE_NANE = "soft_uni";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_NANE);
        final EntityManager entityManager = emf.createEntityManager();

        String query = "SELECT e.department.name,max(e.salary) FROM Employee e" +
                " GROUP BY e.department HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000";

        List<Object[]> results = entityManager.
                createQuery(query, Object[].class).
                getResultList();

        for (Object[] result : results) {
            //  String name = (String) result[0];
            double salary = ((Number) result[1]).doubleValue();
            System.out.printf("%s %.2f%n", result[0], salary);
        }

        entityManager.close();
    }
}

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class E10IncreaseSalaries {
    private static String DATABASE_NANE = "soft_uni";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_NANE);
        final EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        List<String> departments = Arrays.asList("Engineering", "Tool Design", "Marketing", "Information Services");
        String query = "SELECT e FROM Employee e  WHERE e.department.name IN (:departments)";

        List<Employee> employees = entityManager.
                createQuery(query, Employee.class).
                setParameter("departments", departments).
                getResultList();

        employees.
                forEach(e -> {
                    e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));
                    entityManager.persist(e);
                    System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary());
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

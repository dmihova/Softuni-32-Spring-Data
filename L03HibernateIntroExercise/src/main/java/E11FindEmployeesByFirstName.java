import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class E11FindEmployeesByFirstName {
    private static String DATABASE_NANE = "soft_uni";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_NANE);
        final EntityManager entityManager = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();

        String query = "FROM Employee e WHERE e.firstName like :pattern";

        entityManager.
                createQuery(query, Employee.class).
                setParameter("pattern", pattern + "%").
                getResultList()
                .forEach(e -> System.out.printf("%s %s from %s - $%.2f%n",
                        e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary()));

        entityManager.close();
    }
}

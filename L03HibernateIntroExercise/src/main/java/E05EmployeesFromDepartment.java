import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class E05EmployeesFromDepartment {
    private static String DATABASE_NANE = "soft_uni";
    private static String DEPARTMENT = "Research and Development";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_NANE);
        final EntityManager entityManager = emf.createEntityManager();

        String query = "FROM Employee e WHERE e.department.name= :dp ORDER BY e.salary ASC, e.id ASC";

        entityManager.
                createQuery(query, Employee.class).
                setParameter("dp", DEPARTMENT).
                getResultList()
                .forEach(e -> System.out.printf("%s %s from %s - $%.2f%n",
                        e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary()));
        entityManager.close();
    }
}

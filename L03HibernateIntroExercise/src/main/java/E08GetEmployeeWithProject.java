import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class E08GetEmployeeWithProject {
    private static String DATABASE_NANE = "soft_uni";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_NANE);
        final EntityManager entityManager = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        int employeeId = Integer.parseInt(scanner.nextLine());

        Employee employee = entityManager.find(Employee.class, employeeId);
        String projects = employee.getProjects().stream().
                sorted(Comparator.comparing(Project::getName)).
                map(Project::getName).
                collect(Collectors.joining(System.lineSeparator()));

        System.out.println(
                String.format("%s %s - %s%n%s",
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getJobTitle(),
                        projects
                ));

        entityManager.close();
    }
}

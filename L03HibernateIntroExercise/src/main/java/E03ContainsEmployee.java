import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class E03ContainsEmployee {
    private static String DATABASE_NANE = "soft_uni";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        String firstName = input[0];
        String lastName = input[1];

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_NANE);
        final EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "SELECT count(e) FROM Employee e WHERE e.firstName = :first_name AND e.lastName = :last_name";
        Long count = entityManager.
                createQuery(query, Long.class).
                setParameter("first_name", firstName).
                setParameter("last_name", lastName).
                getSingleResult();

        System.out.println(count == 0 ? "No" : "Yes");

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

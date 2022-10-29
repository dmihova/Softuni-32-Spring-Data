import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;

public class E09FindLatest10Projects {

    private static String DATABASE_NANE = "soft_uni";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_NANE);
        final EntityManager entityManager = emf.createEntityManager();

        String query = "FROM Project p ORDER BY p.startDate DESC";
        entityManager.
                createQuery(query, Project.class).
                setMaxResults(10).
                getResultList().
                stream().
                sorted(Comparator.comparing(Project::getName)).
                forEach(System.out::println);

        entityManager.close();
    }
}

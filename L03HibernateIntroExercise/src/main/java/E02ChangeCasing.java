import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Locale;

public class E02ChangeCasing {
    private static String DATABASE_NANE = "soft_uni";
    // private static String UPDATE_TOWNS_WITH_LENGTH_NAME_MORE_THAN_5 =
    //        "UPDATE Town SET name = UPPER(name) WHERE LENGTH(name)>5";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_NANE);
        final EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        // entityManager.createQuery(UPDATE_TOWNS_WITH_LENGTH_NAME_MORE_THAN_5).executeUpdate();
        List<Town> towns = entityManager.createQuery("FROM Town t", Town.class).getResultList();

        for (Town town : towns) {
            final String townName = town.getName();
            if (townName.length() > 5) {
                entityManager.detach(town);
            }
            town.setName(townName.toUpperCase(Locale.ROOT));
        }

        //       towns.stream()
        //                .filter(t -> t.getName().length()< 5)
        //                .forEach(t -> {t.setName(t.getName().toUpperCase()); entityManager.persist(t);
        //                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

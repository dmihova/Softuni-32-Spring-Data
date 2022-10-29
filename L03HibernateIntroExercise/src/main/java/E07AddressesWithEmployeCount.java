import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class E07AddressesWithEmployeCount {
    private static String DATABASE_NANE = "soft_uni";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_NANE);
        final EntityManager entityManager = emf.createEntityManager();

        String query = "FROM Address a ORDER BY a.employees.size DESC";
        List<Address> addresses =
                entityManager.
                        createQuery(query, Address.class).
                        setMaxResults(10).
                        getResultList();

        addresses.
                forEach(a -> System.out.printf("%s %s %s%n",
                        a.getText(),
                        a.getTown() == null ? " " : a.getTown().getName(),
                        a.getEmployees().size())
                );


        entityManager.close();
    }
}


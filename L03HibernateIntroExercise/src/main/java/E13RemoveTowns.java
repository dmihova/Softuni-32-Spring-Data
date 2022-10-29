import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class E13RemoveTowns {
    private static String DATABASE_NANE = "soft_uni";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_NANE);
        final EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String townName = scanner.nextLine();

        List<Town> towns = entityManager.
                createQuery("FROM Town t WHERE t.name=:townName", Town.class).
                setParameter("townName", townName).
                getResultList();
        if (towns.isEmpty()) {
            System.out.println("Town doesn't exist");
            return;
        }
        Town town = towns.get(0);

        String queryAddresses = "FROM Address a WHERE a.town=:town";
        List<Address> addresses = entityManager.
                createQuery("FROM Address a WHERE a.town=:town", Address.class).
                setParameter("town", town).
                getResultList();
        int count = addresses.size();

        for (Address address : addresses) {
            for (Employee employee : address.getEmployees()) {
                employee.setAddress(null);
            }
            entityManager.remove(address);
        }

        entityManager.remove(town);

        entityManager.getTransaction().commit();

        System.out.printf("%d addresses in %s deleted", count, townName);
        entityManager.close();
    }
}

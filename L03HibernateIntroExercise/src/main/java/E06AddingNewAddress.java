import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class E06AddingNewAddress {
    private static String DATABASE_NANE = "soft_uni";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_NANE);
        final EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");
        entityManager.persist(newAddress);

        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();

        String updateEmployee = "UPDATE Employee e SET e.address = :address  WHERE e.lastName = :last_name";
        int count = entityManager.
                createQuery(updateEmployee).
                setParameter("last_name", lastName).
                setParameter("address", newAddress).
                executeUpdate();


//        String queryEmployee = "FROM Employee e WHERE e.lastName=:last_name";
//        entityManager.
//                createQuery(queryEmployee, Employee.class).
//                setParameter("last_name", lastName).
//                getResultList().
//                forEach(e -> System.out.printf("%s %s %s%n",
//                        e.getFirstName(), e.getLastName(),
//                        e.getAddress().getText()));
        if (count > 0) {
            entityManager.getTransaction().commit();
        } else {
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
    }
}

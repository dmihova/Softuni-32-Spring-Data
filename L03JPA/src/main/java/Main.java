import entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("school-db");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        System.out.println("Create students");
        Student student = new Student("JPA1");
        entityManager.persist(student);
        Student student2 = new Student("JPA2");
        entityManager.persist(student2);
        Student student3 = new Student("JPA3");
        entityManager.persist(student3);

//        System.out.println("Find by key");
//        Student student = entityManager.find(Student.class,1);
//        System.out.println(student.getId()+ student.getName());

    /*    System.out.println("Get students");
        List<Student> studentByName = entityManager
                .createQuery("FROM Student", Student.class).getResultList();
        for (Student student:studentByName){
            System.out.println(student.getId()+" "+student.getName());
        }*/

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

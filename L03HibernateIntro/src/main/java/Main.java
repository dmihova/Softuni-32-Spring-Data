import entities.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
// Create students
        //  System.out.println("Create students");
/*       Student student = new Student(1, "S1");
        session.persist(student);
        Student student2 = new Student(2, "S2");
        session.persist(student2);
        Student student3 = new Student(3, "S3");
        session.persist(student3);*/

        // Get 1 student
        // System.out.println("Get 1 student");
        //Student fromDB =session.get(Student.class,1);
       // System.out.println(fromDB.getId()+" "+fromDB.getName());

    /*    System.out.println("Get all student");
        List<Student> students = session.createQuery("FROM Student", Student.class).list();
        for (Student student:students){
            System.out.println(student.getId()+" "+student.getName());
        }

        System.out.println("Get student by name S1");
        List<Student> studentByName = session.createQuery("FROM Student AS s  WHERE s.name='S1'", Student.class).list();
        for (Student student:studentByName){
            System.out.println(student.getId()+" "+student.getName());
        }*/

        System.out.println("CriteriaBuilder");
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery();
        Root<Student> r = criteria.from(Student.class);
        criteria.select(r).where(builder.like(r.get("name"),"S1%"));
        List<Student> studentList = 	session.createQuery(criteria).getResultList();
        for (Student student : studentList) {
            System.out.println(student.getName());
        }



        session.getTransaction().commit();
        session.close();
    }
}

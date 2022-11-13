import hasEntities.CarNtoN;
import hasEntities.DriverNtoN;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("l04-vehicle-db");
        EntityManager  entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

 /*     Vehicle car = new Car("Ford Focus","pertrol",5);
        Vehicle bike =new Bike();
        Vehicle plane =new Plane("Jumbo","pertrol",235);
        entityManager.persist(car);
        entityManager.persist(bike);
        entityManager.persist(plane);*/

        // Car findCar = entityManager.find(Car.class,1);
        //  System.out.println(findCar.getId() +findCar.getModel());

      /*  PlateNumber1to1 pn1 =new PlateNumber1to1("BN343VD");
        entityManager.persist(pn1);
        Car1to1 car1to1 =new Car1to1("test1",pn1);
        entityManager.persist(car1to1);*/

    /*    Company1toN company =new Company1toN("test_company" );
        Plane1toN plane1toNn =new Plane1toN("VBN343VD4");
        company.addPlane(plane1toNn);
        plane1toNn.setCompany(company);
       //entityManager.persist(plane1toNn);
        entityManager.persist(company);*/


        CarNtoN carnn =new CarNtoN("test2c");
        DriverNtoN driver =new DriverNtoN("test2d");
        carnn.addDriver(driver);
     //   driver.addCar(carnn);
        entityManager.persist(driver);
        entityManager.persist(carnn);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

import Entities.WizardDeposit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("l04-gringotts-db");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        WizardDeposit wd = new WizardDeposit( );
        wd.setFirstName("WFN01");
        wd.setLastName("WFL01");
        wd.setAge(50);
        wd.setDepositAmount(BigDecimal.valueOf(666.66));

        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998");
        wd.setDepositStartDate( date1);
        Date date2=new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2025");
        wd.setDepositExpirationDate(date2);

        entityManager.persist(wd);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}

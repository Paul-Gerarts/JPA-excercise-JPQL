package applications;

import entities.Beer;

import javax.persistence.*;
import javax.swing.*;
import java.util.List;

public class GetBeer {


    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("course");
            em = emf.createEntityManager();
            TypedQuery<Beer> allBeersQuery = em.createQuery("select b from Beers as b", Beer.class);
            List<Beer> beers = allBeersQuery.getResultList();
            for (Beer beer : beers) {
                System.out.println(beer);
            }
        } catch (OptimisticLockException ole) {
            JOptionPane.showMessageDialog(null, ole.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}

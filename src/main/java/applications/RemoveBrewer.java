package applications;

import entities.Brewer;

import javax.persistence.*;
import javax.swing.*;
import java.util.Random;

public class RemoveBrewer {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("course");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            TypedQuery<Brewer> brewerQuery = em.createQuery("SELECT b FROM Brewers b WHERE EXISTS (SELECT be FROM Beers be WHERE be.brewer = b.id)", Brewer.class);
            final var brewerResult = brewerQuery.getResultList();
            Brewer brewer = brewerResult.get(new Random().nextInt(brewerResult.size()));
            System.out.println("brewerId for databaseCheck: " + brewer.getId());
            final int amountOfBeers = brewer.getBeers().size();
            System.out.println("amount of beers the brewer had: " + amountOfBeers);
            em.remove(brewer);
            tx.begin();
            em.flush();
            tx.commit();
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

package applications;

import entities.Brewer;

import javax.persistence.*;
import javax.swing.*;
import java.util.Collection;

public class GetBrewer {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("course");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            TypedQuery<Brewer> brewerQuery = em.createQuery("SELECT b FROM Brewers b", Brewer.class);
            brewerQuery
                    .getResultStream()
                    .limit(1)
                    .map(Brewer::getBeers)
                    .flatMap(Collection::stream)
                    .forEach(System.out::println);
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

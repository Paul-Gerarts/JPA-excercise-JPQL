package applications;

import entities.Beer;
import entities.Brewer;
import entities.Category;

import javax.persistence.*;
import javax.swing.*;
import java.util.Random;

public class AddBeer {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("course");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            TypedQuery<Brewer> brewerQuery = em.createQuery("SELECT b FROM Brewers b", Brewer.class);
            final var brewerResult = brewerQuery.getResultList();
            Brewer brewer = brewerResult.get(new Random().nextInt(brewerResult.size()));
            TypedQuery<Category> categoryQuery = em.createQuery("SELECT c FROM Categories c", Category.class);
            final var categoryResult = categoryQuery.getResultList();
            Category category = categoryResult.get(new Random().nextInt(categoryResult.size()));
            Beer newBeer = new Beer(category.getName() + brewer.getName(), 5F, 100, 9F, 1, null, category, brewer);
            em.persist(newBeer);
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

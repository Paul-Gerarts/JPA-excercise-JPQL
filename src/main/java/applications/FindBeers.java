package applications;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;
import javax.swing.*;

public class FindBeers {

    public static void main(String[] args) {

        String[] namedQueries = {
                "findAllBeers",
                "findAllBeersWithKriek",
                "findAllGentBeers",
                "findAllTripelBeers",
                "findAllHeavyCategories",
                "findAllBeersWithDifferentPercentages",
                "findAllVeryHeavyBrewers",
                "findAllPilsBrewers",
                "findAllBeersAveragePrice"
        };

        for (String namedQuery : namedQueries) {
            doQuery(namedQuery);
        }

    }

    private static void doQuery(String name) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("course");
            em = emf.createEntityManager();
            em.createNamedQuery(name).getResultStream().forEach(System.out::println);
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

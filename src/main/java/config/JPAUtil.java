package config;
 
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
 
public class JPAUtil {
    private static EntityManagerFactory emf;
 
    private static synchronized EntityManagerFactory getFactory() {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("klubPU");
        }
        return emf;
    }
 
    public static EntityManager getEntityManager() {
        return getFactory().createEntityManager();
    }
 
    public static synchronized void tutup() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
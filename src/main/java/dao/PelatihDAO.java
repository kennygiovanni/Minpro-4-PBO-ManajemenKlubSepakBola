package dao;

import config.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import model.Pelatih;

public class PelatihDAO {
 
    public boolean tambah(Pelatih p) {
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManager();
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Gagal menambah pelatih: " + e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
 
    public List<Pelatih> getAll() {
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManager();
            return em.createQuery("SELECT p FROM Pelatih p", Pelatih.class).getResultList();
        } catch (Exception e) {
            System.out.println("Gagal mengambil data pelatih: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
}
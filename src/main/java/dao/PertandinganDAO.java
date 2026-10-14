package dao;

import config.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import model.Pertandingan;
 
public class PertandinganDAO {
 
    public boolean tambah(Pertandingan p) {
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
            System.out.println("Gagal menambah pertandingan: " + e.getMessage());
            return false;
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
 
    public List<Pertandingan> getAll() {
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManager();
            return em.createQuery("SELECT p FROM Pertandingan p", Pertandingan.class).getResultList();
        } catch (Exception e) {
            System.out.println("Gagal mengambil data pertandingan: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
}
package dao;

import config.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import model.Pemain;

public class PemainDAO {
   
    public boolean tambah(Pemain p) {
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManager();
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            rollback(em);
            System.out.println("Gagal menambah pemain: " + e.getMessage());
            return false;
        }
        finally {
            tutup(em);
        }
    }
    
    public List<Pemain> getAll() {
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManager();
            return em.createQuery("SELECT p FROM Pemain p", Pemain.class).getResultList();
        }   
        catch (Exception e) {
            System.out.println("Gagal mengambil data pemain: " + e.getMessage());
            return new ArrayList<>();
        } 
        finally {
            tutup(em);
        }
    }
    
    public boolean hapus(int id) {
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManager();
            em.getTransaction().begin();
            Pemain p = em.find(Pemain.class, id);
            if (p == null) {
                em.getTransaction().rollback();
                return false;
        }
        em.remove(p);
        em.getTransaction().commit();
        return true;
        }
        catch (Exception e) {
            rollback(em);
            System.out.println("Gagal menghapus pemain: " + e.getMessage());
            return false;
        } 
        finally {
            tutup(em);
        }
    }
    
    public boolean updateStatus(int id, String statusBaru) {
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManager();
            em.getTransaction().begin();
            Pemain p = em.find(Pemain.class, id);
            if (p == null) {
                em.getTransaction().rollback();
                return false;
            }
            p.setStatusKesehatan(statusBaru); // perubahan terdeteksi otomatis saat commit
            em.getTransaction().commit();
            return true;
        } 
        catch (Exception e) {
            rollback(em);
            System.out.println("Gagal update status pemain: " + e.getMessage());
            return false;
        } 
        finally {
            tutup(em);
        }
    }
    
    private List<Pemain> cariByNama(EntityManager em, String nama) {
        return em.createQuery("SELECT p FROM Pemain p WHERE LOWER(p.nama) = LOWER(:nama)", Pemain.class)
                 .setParameter("nama", nama)
                 .getResultList();
    }
 
    private void rollback(EntityManager em) {
        if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
 
    private void tutup(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}

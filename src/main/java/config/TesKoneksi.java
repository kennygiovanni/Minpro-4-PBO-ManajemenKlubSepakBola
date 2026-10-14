package config;

import jakarta.persistence.EntityManager;

public class TesKoneksi {

    public static void main(String[] args) {
        System.out.println("Tes Koneksi");
        
        try {
            EntityManager em = JPAUtil.getEntityManager();
            em.createNativeQuery("SELECT 1").getSingleResult();
            em.close();
            System.out.println("Koneksi Berhasil");
        } 
        catch (Throwable e) {
            System.out.println("Koneksi Gagal. Penyebab: ");
            Throwable t = e;
            while (t != null) {
                System.out.println(" - " + t.getClass().getName() + ": " + t.getMessage());
                t = t.getCause();
            } 
        }
        finally {
            JPAUtil.tutup();
        }
    }
}
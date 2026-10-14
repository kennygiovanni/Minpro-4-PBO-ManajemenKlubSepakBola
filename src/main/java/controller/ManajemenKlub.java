package controller;

import model.Pertandingan;
import model.Pemain;
import model.Pelatih;
import view.TampilanView;
import dao.PelatihDAO;
import dao.PemainDAO;
import dao.PertandinganDAO;


public class ManajemenKlub {
    private final PemainDAO pemainDAO;
    private final PelatihDAO pelatihDAO;
    private final PertandinganDAO pertandinganDAO;
    
    private final TampilanView tampilanView;
    
    public ManajemenKlub() {
        pemainDAO = new PemainDAO();
        pelatihDAO = new PelatihDAO();
        pertandinganDAO = new PertandinganDAO();
        
        tampilanView = new TampilanView();
    }
        
    public boolean tambahPemain(Pemain p) {
        return pemainDAO.tambah(p);
    }
    
    public void tampilkanSemuaPemain(){
        tampilanView.tampilkanDaftar("Pemain", pemainDAO.getAll());
    }
    
    public boolean updateStatusPemain(int id, String statusBaru){
        return pemainDAO.updateStatus(id, statusBaru);
    }
    
    public boolean hapusPemain(int id){
        return pemainDAO.hapus(id);
    }
    
    public boolean tambahPelatih(Pelatih p){
        return pelatihDAO.tambah(p);
    }
    
    public void tampilkanSemuaPelatih(){
        tampilanView.tampilkanDaftar("Pelatih", pelatihDAO.getAll());
    }
    
    public boolean tambahPertandingan(Pertandingan p){
        return pertandinganDAO.tambah(p);
    }
    
    public void tampilkanRiwayatPertandingan(){
        tampilanView.tampilkanDaftar("Riwayat Pertandingan", pertandinganDAO.getAll());
    }
}        
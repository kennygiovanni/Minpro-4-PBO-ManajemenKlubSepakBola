package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pelatih")
public class Pelatih extends AnggotaKlub{
    @Column(name = "spesialisasi")
    private String spesialisasi;
    @Column(name = "pengalaman")
    private int pengalaman;
    
    protected Pelatih() {}
    
    public Pelatih(String nama, String spesialisasi, int pengalaman) {
        super(nama);
        this.spesialisasi = spesialisasi;
        setPengalaman(pengalaman);
    }
    
    @Override
    public String getPeran() {
        return "Pelatih";
    }
    
    private void setPengalaman(int pengalaman){
        if (pengalaman >= 0 && pengalaman <= 50) {
            this.pengalaman = pengalaman;
        }
        else {
            this.pengalaman = 0;
            System.out.println("Tahun Pengalaman tidak valid, diset ke 0.");
        }
    }

    public String getSpesialisasi() { return spesialisasi; }
    public int getPengalaman() { return pengalaman; }
    
    @Override
    public String getInfo() {
        return super.getInfo()
        + "\nSpesialisasi     : " + spesialisasi
        + "\nPengalaman       : " + pengalaman;
    }     
}
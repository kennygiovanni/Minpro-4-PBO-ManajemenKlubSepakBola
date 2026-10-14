package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "pertandingan")
public class Pertandingan implements Informasi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "lawan")
    private String lawan;
    @Column(name = "tanggal")
    private String tanggal;
    @Column(name = "kompetisi")
    private String kompetisi;
    @Column(name = "skor_klub")
    private int skorKlub;
    @Column(name = "skor_lawan")
    private int skorLawan;
    @Column(name = "lokasi")
    private String lokasi;
 
    protected Pertandingan(){}

    public Pertandingan(String lawan, String tanggal, String kompetisi, int skorKlub, int skorLawan, String lokasi) {
        this.lawan = lawan;
        this.tanggal = tanggal;
        this.kompetisi = kompetisi;
        setSkorKlub(skorKlub);
        setSkorLawan(skorLawan);
        setLokasi(lokasi);
    }
    
    public Pertandingan(String lawan, String tanggal, int skorKlub, int skorLawan, String lokasi) {
        this(lawan, tanggal, "Pertandingan Persahabatan", skorKlub, skorLawan, lokasi);
    }

    private void setSkorKlub(int skorKlub) {
        if (skorKlub >= 0) {
            this.skorKlub = skorKlub;
        } 
        else {
            this.skorKlub = 0;
            System.out.println("Skor tidak boleh negatif, diset ke 0.");
        }
    }

    private void setSkorLawan(int skorLawan) {
        if (skorLawan >= 0) {
            this.skorLawan = skorLawan;
        } 
        else {
            this.skorLawan = 0;
            System.out.println("Skor tidak boleh negatif, diset ke 0.");
        }
    }
    
    private void setLokasi(String lokasi) {
        if (lokasi.equalsIgnoreCase("Kandang") || lokasi.equalsIgnoreCase("Tandang")) {
            this.lokasi = lokasi;
        }
        else {
            this.lokasi = "Kandang";
            System.out.println("Lokasi tidak dikenali, diset ke Kandang.");
        }
    }
    
    private String getHasil() {
        if (skorKlub > skorLawan) {
            return "Menang";
        }
        else if (skorKlub < skorLawan) {
            return "Kalah";
        }
        else{
            return "Seri";
        }
    }
    
    public Integer getId() { return id; }
    public String getLawan() { return lawan; }
    public String getTanggal() { return tanggal; }
    public String getKompetisi() { return kompetisi; }
    public int getSkorKlub() { return skorKlub; }
    public int getSkorLawan() { return skorLawan; }
    public String getLokasi() { return lokasi; }
    
    @Override
    public String getInfo() {
        return "Lawan     : " + lawan
              + "\nTanggal   : " + tanggal
              + "\nKompetisi : " + kompetisi
              + "\nSkor      : " + skorKlub + " - " + skorLawan
              + "\nLokasi    : " + lokasi
              + "\nHasil     : " + getHasil();
    }
}
package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pemain")
public class Pemain extends AnggotaKlub {
    @Column(name = "posisi")
    private String posisi;
    @Column(name = "nomor_punggung")
    private int nomorPunggung;
    @Column(name = "usia")
    private int usia;
    @Column(name = "status_kesehatan")
    private String statusKesehatan;
    
    protected Pemain() {}
    
    public Pemain(String nama, String posisi, int nomorPunggung, int usia, String statusKesehatan) {
        super(nama);
        setPosisi(posisi);
        setNomorPunggung(nomorPunggung);
        setUsia(usia);
        setStatusKesehatan(statusKesehatan);
    }
   
    @Override
    public String getPeran() {
        return "Pemain";
    }
    
    private void setPosisi(String posisi) {
        if (posisi.equalsIgnoreCase("GK") ||
            posisi.equalsIgnoreCase("CB") ||
            posisi.equalsIgnoreCase("LB") ||
            posisi.equalsIgnoreCase("RB") ||
            posisi.equalsIgnoreCase("CDM") ||
            posisi.equalsIgnoreCase("CM") ||
            posisi.equalsIgnoreCase("CAM") ||
            posisi.equalsIgnoreCase("LW") ||
            posisi.equalsIgnoreCase("RW") ||
            posisi.equalsIgnoreCase("ST")) {
            this.posisi = posisi.toUpperCase();
        }
        else {
            this.posisi = "ST";
            System.out.println("Posisi Tidak Tersedia, diset ke ST.");
        }
    }
    
    private void setNomorPunggung(int nomorPunggung){
        if (nomorPunggung >= 1 && nomorPunggung <= 99){
            this.nomorPunggung = nomorPunggung;
        }
        else {
            this.nomorPunggung = 99;
            System.out.println("Nomor Punggung harus 1-99, diset ke 99.");
        }
    }
    
    private void setUsia(int usia){
        if (usia >= 0){
            this.usia = usia;
        }
        else {
            this.usia = 0;
            System.out.println("Usia tidak boleh negatif, diset ke 0.");
        }
    }
    
    public void setStatusKesehatan(String statusKesehatan){
        if (statusKesehatan.equalsIgnoreCase("Tersedia") || statusKesehatan.equalsIgnoreCase("Cedera")) {
            this.statusKesehatan = statusKesehatan.substring(0, 1).toUpperCase() + statusKesehatan.substring(1).toLowerCase();
        }
        else {
           this.statusKesehatan = "Tersedia";
           System.out.println("Status kesehatan harus 'Tersedia' atau 'Cedera', diset ke Tersedia.");
        }
    }
    
    public String getPosisi() { return posisi; }
    public int getNomorPunggung() { return nomorPunggung; }
    public int getUsia() { return usia; }
    public String getStatusKesehatan() { return statusKesehatan; }
    
    @Override
    public String getInfo() {
        return "ID               : "  + getId() + "\n"
             + super.getInfo()
             + "\nPosisi           : " + posisi
             + "\nNo. Punggung     : " + nomorPunggung
             + "\nUsia             : " + usia
             + "\nStatus Kesehatan : " + statusKesehatan;
    }   
}
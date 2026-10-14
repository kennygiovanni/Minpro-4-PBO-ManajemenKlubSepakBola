package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AnggotaKlub implements Informasi{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nama")
    private String nama;
    
    protected AnggotaKlub() {}
    
    public AnggotaKlub(String nama) {
        this.nama = nama;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getNama() {
        return nama;
    }
    
    public abstract String getPeran();
    
    @Override
    public String getInfo() {
        return "Peran            : " + getPeran()
             + "\nNama             : " + nama; 
    }
}
package com.mycompany.miniproject;

import java.util.Scanner;
import model.Pemain;
import model.Pelatih;
import model.Pertandingan;
import controller.ManajemenKlub;
import view.TampilanView;
import config.JPAUtil;

public class Main {

    public static void main(String[] args) {
        
      final Scanner scanner = new Scanner (System.in);
      final ManajemenKlub klub = new ManajemenKlub();
      final TampilanView menuView = new TampilanView();
      int pilihanUtama;
        
      menuView.tampilkanSambutan();
        
      do {
        menuView.tampilkanMenuUtama();
        System.out.print("Pilih Menu: ");  
        
        while (!scanner.hasNextInt()) {
            menuView.tampilkanPesan("Pilihan tidak valid, harus berupa angka (1-4).");
            scanner.next();
            System.out.print("Pilih Menu: ");
        }
        pilihanUtama = scanner.nextInt();
        scanner.nextLine();
        
        switch (pilihanUtama){
            case 1:
                int pilihanPemain;
                do {
                    menuView.tampilkanMenuPemain();
                    System.out.print("Pilih Menu: ");
                    
                    while (!scanner.hasNextInt()) {
                        menuView.tampilkanPesan("Input harus angka, silahkan coba lagi.");
                        scanner.next();
                        System.out.print("Pilih Menu: ");
                    }
                    pilihanPemain = scanner.nextInt();
                    scanner.nextLine();

                    switch (pilihanPemain) {
                        case 1:
                            System.out.print("Nama Pemain: ");
                            String namaPemain = scanner.nextLine();

                            System.out.print("Posisi (GK/CB/LB/RB/CDM/CM/CAM/LW/RW/ST): ");
                            String posisi = scanner.nextLine();

                            System.out.print("Nomor Punggung (1-99): ");
                            while (!scanner.hasNextInt()) {
                                menuView.tampilkanPesan("Pilihan tidak valid, harus berupa angka (1-99).");
                                scanner.next();
                            }
                            int nomor = scanner.nextInt();

                            System.out.print("Usia: ");
                            while (!scanner.hasNextInt()) {
                                menuView.tampilkanPesan("Pilihan tidak valid, harus berupa angka.");
                                scanner.next();
                            }
                            int usia = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Status Kesehatan (Tersedia/Cedera): ");
                            String status = scanner.nextLine();

                            Pemain pemainBaru = new Pemain(namaPemain, posisi, nomor, usia, status);
                            if (klub.tambahPemain(pemainBaru)) {
                                menuView.tampilkanPesan("Pemain Berhasil Ditambahkan");
                            }
                            else { 
                                menuView.tampilkanPesan("Pemain Gagal ditambahkan.");
                            }
                            break;

                        case 2:
                        klub.tampilkanSemuaPemain();
                        menuView.tampilkanPesan("Tekan Enter untuk kembali ke menu");
                        scanner.nextLine();
                        break;

                        case 3:
                            System.out.print("Masukkan ID Pemain: ");
                            while (!scanner.hasNextInt()) {
                                menuView.tampilkanPesan("ID harus berupa angka.");
                                scanner.next();
                                System.out.print("Masukkan ID Pemain: ");
                            }
                            int idHapus = scanner.nextInt();
                            boolean berhasilHapus = klub.hapusPemain(idHapus);
                            if (berhasilHapus) {
                                menuView.tampilkanPesan("Pemain Sudah Dihapus.");
                            }
                            else {
                                menuView.tampilkanPesan("Pemain dengan nama tersebut tidak ada.");
                            }
                            break;

                        case 4:
                            System.out.print("Masukkan ID Pemain: ");
                            while (!scanner.hasNextInt()) {
                                menuView.tampilkanPesan("ID harus berupa angka.");
                                scanner.next();
                                System.out.print("Masukkan ID Pemain: ");
                            }
                            int idUpdate = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Status Kesehatan Baru (Tersedia/Cedera): ");
                            String statusBaru = scanner.nextLine();
                            boolean berhasilUpdate = klub.updateStatusPemain(idUpdate, statusBaru);
                            if (berhasilUpdate) {
                                menuView.tampilkanPesan("Update Status Pemain Berhasil");
                            } 
                            else {
                                menuView.tampilkanPesan("Pemain dengan ID tersebut tidak ditemukan.");
                            }
                            break; 

                        case 5:
                            menuView.tampilkanPesan("Kembali ke menu utama.");
                            break;

                        default:
                            menuView.tampilkanPesan("Pilihan hanya 1-5");
                    }
                
                }
                while (pilihanPemain != 5);
                break;
              
            case 2:
                int pilihanPelatih;
                do {
                    menuView.tampilkanMenuPelatih();
                    System.out.print("Pilih Menu: ");
                    
                    while (!scanner.hasNextInt()) {
                        menuView.tampilkanPesan("Input harus angka, silahkan coba lagi.");
                        scanner.next();
                        System.out.print("Pilih Menu: ");
                    }
                    pilihanPelatih = scanner.nextInt();
                    scanner.nextLine();
                    
                    switch (pilihanPelatih) {
                        case 1:
                            System.out.print("Nama Pelatih: ");
                            String namaPelatih = scanner.nextLine();

                            System.out.print("Spesialisasi: ");
                            String spesialisasi = scanner.nextLine();

                            System.out.print("Pengalaman (tahun): ");
                            while (!scanner.hasNextInt()) {
                                menuView.tampilkanPesan("Pilihan tidak valid, harus berupa angka.");
                                scanner.next();
                            }
                            int pengalaman = scanner.nextInt();
                            scanner.nextLine();

                            Pelatih pelatihBaru = new Pelatih(namaPelatih, spesialisasi, pengalaman);
                            if(klub.tambahPelatih(pelatihBaru)) {
                                menuView.tampilkanPesan("Pelatih Berhasil Ditambahkan");
                            }
                            else {
                                menuView.tampilkanPesan("Pelatih Gagal Ditambahkan.");
                            }
                            break;

                        case 2:
                            klub.tampilkanSemuaPelatih();
                            menuView.tampilkanPesan("Tekan Enter untuk kembali ke menu");
                            scanner.nextLine();
                            break;
                            
                        case 3:
                            menuView.tampilkanPesan("Kembali ke menu utama.");
                            break;
                            
                        default:
                            menuView.tampilkanPesan("Pilihan hanya 1-3.");

                    }
                }
                while (pilihanPelatih != 3);
                break;
                
            case 3:
                int pilihanPertandingan;
                do {
                    menuView.tampilkanMenuPertandingan();
                    System.out.print("Pilih Menu: ");
                    
                    while (!scanner.hasNextInt()) {
                        menuView.tampilkanPesan("Input harus angka, silahkan coba lagi.");
                        scanner.next();
                        System.out.print("Pilih Menu: ");
                    }
                    pilihanPertandingan = scanner.nextInt();
                    scanner.nextLine();
                    
                    switch (pilihanPertandingan) {
                        case 1:
                            System.out.print("Masukkan Nama Tim Lawan: ");
                            String lawan = scanner.nextLine();

                            System.out.print("Tanggal (dd-mm-yy): ");
                            String tanggal = scanner.nextLine();

                            System.out.print("Kompetisi: ");
                            String kompetisi = scanner.nextLine();

                            System.out.print("Skor Klub Kita: ");
                            while (!scanner.hasNextInt()) {
                                menuView.tampilkanPesan("Pilihan tidak valid, harus berupa angka.");
                                scanner.next();
                            }
                            int skorKlub = scanner.nextInt();

                            System.out.print("Skor Klub Lawan: ");
                            while (!scanner.hasNextInt()) {
                                menuView.tampilkanPesan("Pilihan tidak valid, harus berupa angka.");
                                scanner.next();
                            }
                            int skorLawan = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Lokasi (Kandang/Tandang): ");
                            String lokasi = scanner.nextLine();

                            Pertandingan p = new Pertandingan(lawan, tanggal, kompetisi, skorKlub, skorLawan, lokasi);

                            if (klub.tambahPertandingan(p)) {
                               menuView.tampilkanPesan("Pertandingan Berhasil Ditambahkan "); 
                            }
                            else {
                                menuView.tampilkanPesan("Pertandingan Gagal Ditambahkan.");
                            }
                            break;
                            
                        case 2:
                            klub.tampilkanRiwayatPertandingan();
                            menuView.tampilkanPesan("Tekan Enter untuk kembali ke menu");
                            scanner.nextLine();
                            break;

                        case 3:
                            menuView.tampilkanPesan("Kembali ke menu utama.");
                            break;
                            
                        default:
                            menuView.tampilkanPesan("Pilihan hanya 1-3.");
                            
                    }
                }
                while(pilihanPertandingan !=3);
                break;

            case 4:
                menuView.tampilkanPesan("Program Selesai.");
                break;

            default:
                menuView.tampilkanPesan("Pilihan tidak valid, harus berupa angka 1-4.");
        }
      } 
      while (pilihanUtama != 4);

      scanner.close();
      JPAUtil.tutup();
    }
}
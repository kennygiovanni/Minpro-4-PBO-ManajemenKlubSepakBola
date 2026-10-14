# Sistem Manajemen Klub Sepak Bola

## Deskripsi Singkat Program

Program ini adalah aplikasi berbasis console (command line) yang dibuat menggunakan bahasa pemrograman Java dengan penerapan konsep Pemrograman Berorientasi Objek. Program ini berfungsi sebagai sistem manajemen sederhana untuk klub sepak bola, yang dapat digunakan oleh seorang manajer klub untuk mengelola data pemain, data pelatih, serta riwayat pertandingan klub. Seluruh data disimpan di database MySQL (`klub_sepakbola`), sehingga tidak hilang saat program ditutup.

Program ini membantu manajer klub dalam:
- **Mencatat skuad klub**: data pemain dan pelatih terorganisir, termasuk pemain mana yang sedang cedera.
- **Memantau hasil pertandingan**: riwayat pertandingan tersimpan beserta hasilnya (Menang/Kalah/Seri) yang dihitung otomatis dari skor.

Program ini dibangun dengan menerapkan beberapa konsep berikut:
- Class dan Object
- Constructor (termasuk constructor overloading)
- Encapsulation (access modifier `private` dan `public`, getter & setter, validasi)
- Inheritance (pewarisan)
- Abstraction (abstract class, abstract method, dan interface)
- Polymorphism (method overriding dan overloading)
- Struktur MVC dengan lapisan DAO (Data Access Object)
- Akses database dengan **JDBC**
- Pemetaan objek ke tabel dengan **ORM (JPA/Hibernate)**
- Validasi input pada setiap data yang dimasukkan pengguna

**Teknologi:** Java, Maven, MySQL, Hibernate ORM 6.4.4.Final, MySQL Connector/J 8.3.0.

> Saat program dijalankan, data dibaca dari database. Tabel yang digunakan: `pemain`, `pelatih`, dan `pertandingan`, masing-masing dengan kolom `id` sebagai primary key (auto increment).

## Struktur Package

```
Mini_Project/
├── pom.xml
└── src/main/
    ├── java/
    │   ├── com/mycompany/miniproject/
    │   │   └── Main.java
    │   ├── config/
    │   │   ├── JPAUtil.java
    │   │   └── TesKoneksi.java
    │   ├── controller/
    │   │   └── ManajemenKlub.java
    │   ├── dao/
    │   │   ├── PemainDAO.java
    │   │   ├── PelatihDAO.java
    │   │   └── PertandinganDAO.java
    │   ├── model/
    │   │   ├── Informasi.java
    │   │   ├── AnggotaKlub.java
    │   │   ├── Pemain.java
    │   │   ├── Pelatih.java
    │   │   └── Pertandingan.java
    │   └── view/
    │       └── TampilanView.java
    └── resources/
        └── META-INF/
            └── persistence.xml
```

| Package | Isi | Peran |
|---|---|---|
| `model` | `Informasi`, `AnggotaKlub`, `Pemain`, `Pelatih`, `Pertandingan` | Menyimpan data dan aturan validasi. Pada versi ORM, class ini juga berperan sebagai entity. |
| `view` | `TampilanView` | Menampilkan data dan menu ke layar |
| `controller` | `ManajemenKlub` | Menghubungkan Main dengan DAO dan View |
| `dao` | `PemainDAO`, `PelatihDAO`, `PertandinganDAO` | Satu-satunya lapisan yang berinteraksi langsung dengan database |
| `config` | `JPAUtil`, `TesKoneksi` | Penyedia koneksi database (`EntityManager`) dan pengujian koneksi |
| `com.mycompany.miniproject` | `Main` | Entry point program, menangani input pengguna |
| `resources/META-INF` | `persistence.xml` | Konfigurasi JPA: koneksi database dan daftar entity |

## Penjelasan Alur Program

1. **Program dimulai** dengan membuat satu objek `ManajemenKlub` dan satu `TampilanView`. `ManajemenKlub` membuat objek `PemainDAO`, `PelatihDAO`, dan `PertandinganDAO`. Data tidak diisi dari kode, tetapi dibaca dari database saat dibutuhkan.

2. **Menu utama ditampilkan** secara berulang menggunakan perulangan `do-while`, sehingga program terus berjalan selama pengguna belum memilih menu keluar. Menu yang tersedia adalah:
   - Menu Pemain
   - Menu Pelatih
   - Menu Pertandingan
   - Keluar

3. **Pengguna memilih menu** dengan memasukkan angka melalui keyboard (`Scanner`). Program memvalidasi input agar hanya menerima angka; jika bukan angka, pengguna diminta memasukkan ulang.

4. **Setiap menu memiliki submenu** dengan perulangan sendiri yang berakhir saat pengguna memilih "Kembali ke Menu Utama":
   - **Menu Pemain**: Tambah Pemain, Lihat Semua Pemain, Hapus Pemain, Update Status Pemain, Kembali.
   - **Menu Pelatih**: Tambah Pelatih, Lihat Semua Pelatih, Kembali.
   - **Menu Pertandingan**: Tambah Pertandingan, Lihat Riwayat Pertandingan, Kembali.

5. **Program memproses pilihan** menggunakan struktur `switch-case`. Alur datanya:

```
Main ──► ManajemenKlub ──► DAO ──► JPAUtil/EntityManager (Hibernate) ──► MySQL
  ▲                         │
  └──── TampilanView ◄──────┘  (hasil ditampilkan ke layar)
```

   - **Tambah Pemain/Pelatih/Pertandingan**: `Main` meminta data satu per satu lalu membuat objek (`Pemain`, `Pelatih`, atau `Pertandingan`). Constructor objek ini memvalidasi data. Objek diserahkan ke `ManajemenKlub`, diteruskan ke DAO, lalu disimpan ke database dalam satu transaksi. DAO mengembalikan `true` atau `false`, dan `Main` menampilkan pesan **berhasil atau gagal sesuai hasil sebenarnya**.
   - **Lihat Data**: `ManajemenKlub` mengambil daftar dari DAO, lalu `TampilanView` menampilkan seluruh data dengan perulangan `for-each`. Daftar pemain menampilkan **ID** pemain.
   - **Hapus/Update Status Pemain**: pengguna memasukkan **ID pemain** (yang terlihat di daftar pemain). DAO mencari pemain berdasarkan ID, lalu menghapus atau memperbarui datanya. Jika ID tidak ditemukan, program menampilkan pesan bahwa pemain tidak ditemukan.
   - Setiap data yang dimasukkan melalui proses **validasi** (posisi harus sesuai daftar, skor tidak boleh negatif, status kesehatan harus sesuai pilihan, dan seterusnya).

6. **Setelah satu proses selesai**, program kembali menampilkan submenu yang sama (karena berada di dalam perulangan), sehingga pengguna dapat memilih aksi lain tanpa menjalankan ulang program.

7. **Program berhenti** hanya ketika pengguna memilih **Keluar (angka 4)** pada menu utama. Saat itu `Scanner` dan `EntityManagerFactory` (`JPAUtil.tutup()`) ditutup.

## Penerapan Encapsulation

Encapsulation diterapkan pada seluruh class di package `model` (`AnggotaKlub`, `Pemain`, `Pelatih`, `Pertandingan`), dengan cara:

- Seluruh atribut dideklarasikan dengan access modifier **`private`**, sehingga tidak bisa diakses langsung dari luar class.
- Akses atribut dilakukan melalui method **getter** (membaca nilai) dan **setter** (mengubah nilai). Setter yang hanya dipakai constructor dibuat `private`, sedangkan yang dipanggil dari luar (misalnya `setStatusKesehatan()` saat update status) dibuat `public`.
- Setiap setter dilengkapi dengan **validasi**, sehingga data yang masuk selalu valid:

| Class | Atribut | Aturan | Jika tidak valid |
|---|---|---|---|
| `Pemain` | `posisi` | GK, CB, LB, RB, CDM, CM, CAM, LW, RW, ST | diset ke `ST` |
| `Pemain` | `nomorPunggung` | 1 sampai 99 | diset ke `99` |
| `Pemain` | `usia` | tidak boleh negatif | diset ke `0` |
| `Pemain` | `statusKesehatan` | `Tersedia` atau `Cedera` | diset ke `Tersedia` |
| `Pelatih` | `pengalaman` | 0 sampai 50 tahun | diset ke `0` |
| `Pertandingan` | `skorKlub`, `skorLawan` | tidak boleh negatif | diset ke `0` |
| `Pertandingan` | `lokasi` | `Kandang` atau `Tandang` | diset ke `Kandang` |

- Atribut `id` di `AnggotaKlub` bersifat `private` dan hanya bisa dibaca lewat `getId()`. Subclass seperti `Pemain` juga memakai `getId()`, tidak mengakses field-nya langsung.
- Method `private getHasil()` pada `Pertandingan` bagian dari encapsulation, karena hasil pertandingan hanya dihitung di dalam class itu sendiri.

> **Catatan ORM:** Hibernate membutuhkan constructor tanpa parameter dan atribut yang tidak `final`. Karena itu, constructor kosong dibuat `protected` (tidak bisa dipanggil sembarangan dari luar), dan keyword `final` pada atribut dan class dilepas. Validasi tetap berlaku karena objek yang dibuat lewat program selalu melewati constructor yang memvalidasi.

## Penerapan Inheritance

Inheritance diterapkan antara class `AnggotaKlub` sebagai **superclass (parent class)**, dengan `Pemain` dan `Pelatih` sebagai **subclass (child class)**.

```
AnggotaKlub (superclass, abstract)
├── atribut: id, nama
│
├── Pemain extends AnggotaKlub
│   └── tambahan atribut: posisi, nomorPunggung, usia, statusKesehatan
│
└── Pelatih extends AnggotaKlub
    └── tambahan atribut: spesialisasi, pengalaman
```

Alasan penerapannya: `Pemain` dan `Pelatih` sama-sama **anggota klub** yang memiliki atribut `id` dan `nama`. Daripada menulisnya dua kali, atribut tersebut didefinisikan satu kali di `AnggotaKlub`, lalu diwariskan memakai `extends`.

Pada constructor subclass, `super(nama)` memanggil constructor `AnggotaKlub`. Pada `getInfo()`, subclass memanggil `super.getInfo()` untuk memakai informasi dasar milik parent (peran dan nama), lalu menambahkan informasi khususnya sendiri:

```java
public class Pelatih extends AnggotaKlub {
    private String spesialisasi;
    private int pengalaman;

    public Pelatih(String nama, String spesialisasi, int pengalaman) {
        super(nama);                       // memanggil constructor parent
        this.spesialisasi = spesialisasi;
        setPengalaman(pengalaman);
    }

    @Override
    public String getInfo() {
        return super.getInfo()             // memakai info dari parent
        + "\nSpesialisasi     : " + spesialisasi
        + "\nPengalaman       : " + pengalaman;
    }
}
```

Pada versi ORM, `AnggotaKlub` ditandai `@MappedSuperclass` sehingga kolom `id` dan `nama` ikut terpetakan ke tabel `pemain` dan `pelatih`.

## Penerapan Abstraction

Abstraction diterapkan lewat **abstract class `AnggotaKlub`** yang memiliki **abstract method `getPeran()`**, yaitu method tanpa isi yang wajib diimplementasikan oleh setiap subclass:

```java
// di AnggotaKlub
public abstract String getPeran();

// di Pemain
@Override
public String getPeran() { return "Pemain"; }

// di Pelatih
@Override
public String getPeran() { return "Pelatih"; }
```

Manfaatnya:
- `AnggotaKlub` **tidak bisa diinstansiasi** (`new AnggotaKlub(...)` akan error), karena "anggota klub" hanyalah konsep umum.
- Jika ada subclass baru yang lupa mengimplementasikan `getPeran()`, compiler langsung menolaknya.
- `getInfo()` di parent memanggil `getPeran()` tanpa tahu isinya. Detail diserahkan ke subclass.

### Interface

Interface **`Informasi`** berisi satu kontrak:

```java
public interface Informasi {
    String getInfo();
}
```

- `AnggotaKlub` dan `Pertandingan` sama-sama `implements Informasi`. Kewajiban memiliki `getInfo()` otomatis diturunkan ke `Pemain` dan `Pelatih`.
- `Pertandingan` bukan anggota klub sehingga tidak bisa mewarisi `AnggotaKlub`. Lewat interface ini, ia tetap bisa diperlakukan sama saat ditampilkan.
- `TampilanView` hanya mengenal `Informasi` dan tidak perlu meng-import `Pemain`, `Pelatih`, atau `Pertandingan`.

## Penerapan Polymorphism

Polymorphism diterapkan melalui **method overriding** pada `getInfo()` dan **method overloading** pada constructor `Pertandingan`.

- `getInfo()` pertama kali didefinisikan di interface `Informasi`, lalu diimplementasikan di `AnggotaKlub` (informasi dasar berupa peran dan nama) dan `Pertandingan`.
- Method ini di-**override** (ditandai `@Override`) di `Pemain` dan `Pelatih`, masing-masing menambahkan informasi khusus miliknya (`Pemain`: ID, posisi, nomor punggung, usia, status kesehatan; `Pelatih`: spesialisasi, pengalaman).

Pemanfaatannya terlihat di `TampilanView`, yang memakai satu method untuk menampilkan ketiga jenis daftar:

```java
public void tampilkanDaftar(String judul, List<? extends Informasi> daftar) {
    if (daftar.isEmpty()) {
        System.out.println("Belum ada data " + judul.toLowerCase());
        return;
    }
    System.out.println("=== DAFTAR " + judul.toUpperCase() + " ===");
    for (Informasi i : daftar) {
        tampilkanInfo(i);          // i.getInfo() dipilih saat program berjalan
    }
}
```

Method ini dipanggil untuk list `Pemain`, `Pelatih`, maupun `Pertandingan`. Meski ketiganya dilewatkan ke method yang sama, hasil keluarannya berbeda, karena Java menjalankan versi `getInfo()` sesuai tipe objek sebenarnya saat program berjalan (*dynamic binding*).

Selain itu, `Pertandingan` memiliki dua constructor dengan parameter berbeda (**overloading**). Versi tanpa parameter kompetisi otomatis mengisi "Pertandingan Persahabatan":

```java
public Pertandingan(String lawan, String tanggal,
                    int skorKlub, int skorLawan, String lokasi) {
    this(lawan, tanggal, "Pertandingan Persahabatan", skorKlub, skorLawan, lokasi);
}
```

## Penerapan JDBC

Pada tahap awal pengembangan, akses database memakai **JDBC** (Java Database Connectivity) secara manual. Pendekatan ini kemudian digantikan ORM (lihat bagian berikutnya), dan class `Koneksi` tidak lagi dipakai di versi akhir.

### Koneksi

Class `Koneksi` di package `config` menyimpan URL, user, dan password, lalu membuka koneksi memakai `DriverManager`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/klub_sepakbola";

public static Connection getConnection() {
    try {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL, USER, PASS);
        }
    } catch (SQLException e) {
        System.out.println("Gagal Konek: " + e.getMessage());
    }
    return conn;
}
```

Koneksi disimpan dalam satu variabel `static` yang dipakai bersama (pola singleton) dan dibuat ulang bila tertutup.

### Operasi di DAO

Setiap operasi menulis SQL sendiri memakai `PreparedStatement`, yang mencegah SQL Injection karena nilai dikirim sebagai parameter:

```java
String sql = "INSERT INTO pemain (nama, posisi, nomor_punggung, usia, status_kesehatan) VALUES (?, ?, ?, ?, ?)";
try (PreparedStatement ps = conn.prepareStatement(sql)) {
    ps.setString(1, p.getNama());
    ps.setString(2, p.getPosisi());
    ps.setInt(3, p.getNomorPunggung());
    ps.setInt(4, p.getUsia());
    ps.setString(5, p.getStatusKesehatan());
    return ps.executeUpdate() > 0;
}
```

Untuk membaca data, hasil query ditampung di `ResultSet` lalu dipetakan **satu per satu** menjadi objek:

```java
while (rs.next()) {
    daftar.add(new Pemain(
        rs.getString("nama"),
        rs.getString("posisi"),
        rs.getInt("nomor_punggung"),
        rs.getInt("usia"),
        rs.getString("status_kesehatan")));
}
```

### Catatan JDBC

- `Connection` bersama tidak boleh ditutup di tiap method. Cukup `PreparedStatement` dan `ResultSet` yang ditutup (lewat try-with-resources).
- Kekurangannya: SQL ditulis dan dipetakan secara manual, sehingga kode DAO panjang dan rawan salah tipe atau urutan parameter.

## Penerapan ORM

**ORM (Object Relational Mapping)** memetakan class Java ke tabel database, sehingga data diakses sebagai objek tanpa menulis SQL dan `ResultSet` secara manual. Program ini memakai **JPA** dengan **Hibernate** sebagai implementasinya.

### 1. Dependency (`pom.xml`)

```xml
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.4.4.Final</version>
</dependency>
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.3.0</version>
</dependency>
```

### 2. Konfigurasi (`META-INF/persistence.xml`)

Berisi persistence unit `klubPU`, daftar entity, serta URL, user, dan password database (pindahan dari `Koneksi`). Pengaturan `hibernate.hbm2ddl.auto = none` membuat Hibernate **tidak mengubah** tabel yang sudah ada.

### 3. Pemetaan Entity

| Class | Tabel | Anotasi utama |
|---|---|---|
| `AnggotaKlub` | (diwarisi) | `@MappedSuperclass`, `@Id`, `@GeneratedValue(IDENTITY)`, `@Column` |
| `Pemain` | `pemain` | `@Entity`, `@Table(name = "pemain")`, `@Column` |
| `Pelatih` | `pelatih` | `@Entity`, `@Table(name = "pelatih")`, `@Column` |
| `Pertandingan` | `pertandingan` | `@Entity`, `@Table(name = "pertandingan")`, `@Id`, `@Column` |

Contoh:

```java
@Entity
@Table(name = "pemain")
public class Pemain extends AnggotaKlub {
    @Column(name = "posisi")
    private String posisi;

    @Column(name = "nomor_punggung")
    private int nomorPunggung;

    protected Pemain() { }   // constructor kosong, wajib untuk Hibernate
    ...
}
```

### 4. `JPAUtil`

Menggantikan `Koneksi`. Menyediakan `EntityManager` dari `EntityManagerFactory` bernama `klubPU`, dan menutupnya lewat `tutup()` saat program selesai.

### 5. DAO dengan ORM

DAO tidak lagi menulis SQL `INSERT/UPDATE/DELETE`:

```java
// Tambah
em.getTransaction().begin();
em.persist(pemain);
em.getTransaction().commit();

// Lihat semua (JPQL)
em.createQuery("SELECT p FROM Pemain p", Pemain.class).getResultList();

// Hapus berdasarkan ID
Pemain p = em.find(Pemain.class, id);
em.remove(p);

// Update status: cukup ubah objeknya, lalu commit
p.setStatusKesehatan(statusBaru);
```

Pada update, Hibernate mendeteksi perubahan objek (*dirty checking*) dan membuat query `UPDATE` otomatis saat `commit`. Jika terjadi kesalahan, transaksi di-`rollback`.

### Perbandingan JDBC dan ORM

| Aspek | JDBC | ORM (JPA/Hibernate) |
|---|---|---|
| Konfigurasi database | Konstanta di `Koneksi.java` | `persistence.xml` |
| Query | String SQL manual | `persist()`, `find()`, `remove()`, JPQL |
| Pengisian objek | `ResultSet` dipetakan manual | Otomatis oleh Hibernate |
| Update data | `UPDATE ... SET` manual | Ubah objek lalu `commit` |
| Pengelolaan koneksi | `Connection` static bersama | `EntityManager` dibuat dan ditutup per operasi |
| Panjang kode DAO | Panjang | Lebih ringkas |

## Cara Menjalankan Program

1. Hidupkan MySQL dan pastikan database `klub_sepakbola` beserta tabel `pemain`, `pelatih`, dan `pertandingan` sudah ada (masing-masing dengan kolom `id` auto increment).

2. Pastikan struktur proyek sesuai:

```
src/main/java/model/...
src/main/java/dao/...
src/main/java/config/...
src/main/java/controller/ManajemenKlub.java
src/main/java/view/TampilanView.java
src/main/java/com/mycompany/miniproject/Main.java
src/main/resources/META-INF/persistence.xml
```

3. Pastikan dependency Hibernate dan MySQL Connector sudah ada di `pom.xml`, serta kredensial database di `persistence.xml` sudah sesuai.

4. Build proyek (**Clean and Build** di NetBeans, atau `mvn clean compile`).

5. Uji koneksi dengan menjalankan `config/TesKoneksi.java`. Hasil yang diharapkan: `Koneksi Berhasil`.

6. Jalankan program melalui `com/mycompany/miniproject/Main.java` (Run File di NetBeans/IDE).

## Screenshots Program

Berikut adalah dokumentasi visual antarmuka dan keluaran program saat dijalankan di terminal/console:

### 1. Uji Koneksi Database
> Hasil menjalankan `TesKoneksi` yang menampilkan "Koneksi Berhasil" ke database `klub_sepakbola`.

![Uji Koneksi](Screenshots/00-tes-koneksi.png)

---

### 2. Tampilan Menu Utama
> Menampilkan menu pilihan sistem manajemen klub saat pertama kali program dijalankan.

![Tampilan Menu Utama](Screenshots/01-menu-utama.png)

---

### 3. Fitur Manajemen Pemain
#### A. Menu Pemain
> Submenu yang muncul setelah memilih angka 1, berisi pilihan tambah, lihat, hapus, update status, dan kembali.

![Menu Pemain](Screenshots/02-menu-pemain.png)

#### B. Tambah Data Pemain (Input & Validasi)
> Proses menginput data pemain baru yang disimpan ke database.

![Tambah Pemain](Screenshots/03-tambah-pemain.png)

#### C. Lihat Daftar Pemain
> Output daftar seluruh pemain dari database beserta **ID**-nya, memanfaatkan method `getInfo()` lewat `tampilkanDaftar()`.

![Lihat Daftar Pemain](Screenshots/04-lihat-pemain.png)

#### D. Hapus Pemain
> Proses penghapusan data pemain dari database berdasarkan **ID** yang diinputkan pengguna.

![Hapus Pemain](Screenshots/05-hapus-pemain.png)

#### E. Update Status Pemain
> Pembaruan status kesehatan pemain (Tersedia/Cedera) berdasarkan **ID** pemain.

![Update Status Pemain](Screenshots/06-update-status-pemain.png)

---

### 4. Fitur Manajemen Pelatih
#### A. Menu Pelatih
> Submenu yang muncul setelah memilih angka 2, berisi pilihan tambah pelatih, lihat daftar pelatih, dan kembali.

![Menu Pelatih](Screenshots/07-menu-pelatih.png)

#### B. Tambah Pelatih
> Menginput data pelatih baru beserta spesialisasi dan pengalaman melatih.

![Tambah Pelatih](Screenshots/08-tambah-pelatih.png)

#### C. Lihat Daftar Pelatih
> Menampilkan daftar pelatih yang tersimpan di database.

![Lihat Daftar Pelatih](Screenshots/09-lihat-pelatih.png)

---

### 5. Fitur Manajemen Pertandingan
#### A. Menu Pertandingan
> Submenu yang muncul setelah memilih angka 3, berisi pilihan tambah pertandingan, lihat riwayat pertandingan, dan kembali.

![Menu Pertandingan](Screenshots/10-menu-pertandingan.png)

#### B. Tambah Pertandingan
> Mengisi riwayat pertandingan baru meliputi tim lawan, tanggal, kompetisi, skor, dan lokasi pertandingan.

![Tambah Pertandingan](Screenshots/11-tambah-pertandingan.png)

#### C. Lihat Riwayat Pertandingan
> Menampilkan riwayat seluruh pertandingan, lengkap dengan hasil (Menang/Kalah/Seri) yang dihitung otomatis dari skor.

![Lihat Riwayat Pertandingan](Screenshots/12-lihat-pertandingan.png)

---

### 6. Keluar dari Program
> Penutupan program ketika pengguna memilih menu angka 4 pada menu utama.

![Keluar Program](https://github.com/kennygiovanni/Minpro-4-PBO-ManajemenKlubSepakBola/blob/master/screenshots/Screenshot%202026-10-14%20193253.png)

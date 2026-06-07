# JTKLearn Web Automation Testing Project

Project ini adalah implementasi *test automation* untuk platform **JTKLearn** menggunakan **Java, Selenium WebDriver, Cucumber BDD (Behavior-Driven Development)**, dan pola desain **Page Object Model (POM)**. 

Proyek ini dirancang secara kolaboratif sehingga beberapa pengembang/tester (Zaky, Hasbi, Deus) dapat menambahkan test case masing-masing tanpa terjadi konflik kode.

---

## 🚀 Fitur yang Diuji (Zaky - Orang 1)
1. **TC-FR-1.2 (Login Pelajar Berhasil)**: Memverifikasi proses autentikasi pelajar menggunakan email dan password valid, serta memastikan menu navigasi header termuat dengan benar.
2. **TC-FR-6.0.2 (Berhasil Daftar Course)**: Memvalidasi alur otomatisasi pendaftaran kursus (course enrollment) menggunakan kode pendaftaran (*enrollment key*).

---

## 📂 Struktur Folder Proyek
Struktur proyek disusun rapi menggunakan folder khusus per kontributor untuk mencegah konflik Git:

```text
automation-testing/
│
├── pom.xml                                      # Konfigurasi Maven & Dependensi
├── README.md                                    # Dokumentasi Utama Proyek
├── LAPORAN_PEMAHAMAN.md                         # Laporan Pemahaman Web Automation
│
└── src/
    └── test/
        ├── java/
        │   ├── pages/                           # Page Object Model (Shared)
        │   │   ├── LoginPage.java               # Page Object untuk halaman Login
        │   │   ├── DashboardPage.java           # Page Object untuk halaman Dashboard
        │   │   └── CourseOverviewPage.java      # Page Object untuk halaman Detail Kursus
        │   │
        │   ├── stepdefinitions/                 # Cucumber Step Definitions
        │   │   ├── Hooks.java                   # Setup, Teardown & Screenshot on Failure (Shared)
        │   │   └── zaky/                        # Folder khusus Step Definitions milik Zaky
        │   │       ├── TC_FR_1_2_LoginSteps.java
        │   │       └── TC_FR_6_0_2_CourseRegistrationSteps.java
        │   │
        │   ├── runners/                         # Cucumber Test Runner
        │   │   └── TestRunner.java
        │   │
        │   └── utils/                           # Driver Utilities
        │       └── DriverFactory.java
        │
        └── resources/
            └── features/                        # Gherkin Feature Files
                └── zaky/                        # Folder khusus berkas .feature milik Zaky
                    ├── TC_FR_1_2_Authentication.feature
                    └── TC_FR_6_0_2_CourseRegistration.feature
```

---

## 💻 Cara Menjalankan Test

### 1. Menjalankan Test Case Milik Zaky (Default)
Secara default, `TestRunner.java` dikonfigurasi untuk menjalankan semua test case milik Zaky di folder `features/zaky`:
```bash
# Menjalankan secara headless (tanpa GUI browser)
mvn test

# Menjalankan secara visual (headed mode)
mvn test -Dheadless=false
```

### 2. Menjalankan Semua Test Case (Milik Zaky, Hasbi, & Deus) Sekaligus
Ubah parameter `features` pada [TestRunner.java](file:///d:/Kampus/Semester%206/Software%20Testing/Praktik/Pertemuan%2014/Sample%20Project/automation-testing/src/test/java/runners/TestRunner.java) agar mengarah ke folder induk:
```java
@CucumberOptions(
        features = "src/test/resources/features", // Tanpa subfolder /zaky
        glue = "stepdefinitions",                  // Tetap stepdefinitions agar Hooks.java terpanggil
        plugin = { ... }
)
```

---

## 🤝 Panduan Kolaborasi (Cara Menambah Test Case Baru)

Bagi kontributor lain (**Hasbi**, **Deus**, dll.) yang ingin menambahkan test case baru, silakan ikuti panduan struktur berikut agar kode tetap bersih dan bebas konflik:

### Langkah 1: Buat Skenario Fitur (`.feature`)
* Buat subfolder dengan nama Anda di dalam `src/test/resources/features/` (contoh: `features/hasbi/` atau `features/deus/`).
* Buat berkas skenario menggunakan format BDD Gherkin dengan prefix Kode Test Case (contoh: `TC_FR_1_8_LoginFailed.feature`).

### Langkah 2: Gunakan Page Object Model (`pages/`) yang Sudah Ada
* **PENTING**: Jangan menduplikasi halaman web! Jika elemen halaman sudah ada di `LoginPage.java`, `DashboardPage.java`, atau `CourseOverviewPage.java`, gunakan kelas tersebut.
* Jika Anda menguji halaman baru yang belum ada representasi kelasnya, buat kelas Java baru di dalam folder `src/test/java/pages/` (tanpa prefix nama orang/test case, karena halaman web bersifat *shared*).

### Langkah 3: Buat Step Definitions Baru
* Buat subfolder dengan nama Anda di dalam `src/test/java/stepdefinitions/` (contoh: `stepdefinitions/hasbi/`).
* Buat berkas Step Definition baru di dalam folder tersebut (contoh: `TC_FR_1_8_LoginFailedSteps.java`).
* Pastikan baris pertama deklarasi package berkas Java Anda mengarah ke sub-package Anda sendiri:
  ```java
  package stepdefinitions.hasbi;
  ```

### Langkah 4: Jalankan Test Case Anda Mandiri
Sebelum melakukan *commit* atau *push*, Anda bisa menguji berkas Anda sendiri dengan mengganti `features` di `TestRunner.java` ke folder Anda:
```java
features = "src/test/resources/features/hasbi"
```

---

## 📊 Laporan Hasil Pengujian (Test Reports)
Setelah pengujian selesai dijalankan, Cucumber akan otomatis membuat laporan di dalam folder `target/`:
* **HTML Report**: `target/cucumber-report.html` (Laporan interaktif lengkap dengan penataan visual dan screenshot kegagalan).
* **JSON Report**: `target/cucumber-report.json`

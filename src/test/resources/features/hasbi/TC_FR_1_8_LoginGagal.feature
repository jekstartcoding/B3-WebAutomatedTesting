# TC-FR-1.8 | Authentication | Negatif
# Verifikasi login sebagai pelajar gagal karena kredensial tidak valid

Feature: Authentication

  Scenario: Login sebagai pelajar gagal karena kredensial tidak valid
    Given user berada di halaman login dengan URL "https://polban-space.cloudias79.com/jtk-learn/"
    When memasukkan email pelajar "salahuser@contoh.com" dan password tidak valid "salapass"
    And menekan tombol Masuk
    Then sistem menampilkan pesan error bahwa kredensial tidak valid
    And user tetap berada di halaman login

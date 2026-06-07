Feature: Authentication

  Scenario: Verifikasi login berhasil dengan kredensial Pelajar yang valid
    Given user pelajar berada di halaman Login
    When memasukkan email pelajar "jeki@example.com" dan password valid "jeki"
    And menekan tombol Masuk
    Then sistem berhasil autentikasi dan diarahkan ke Dashboard Pelajar
    And header menampilkan menu Beranda, Kursus Saya, Riwayat Kuis, dan Nama Akun

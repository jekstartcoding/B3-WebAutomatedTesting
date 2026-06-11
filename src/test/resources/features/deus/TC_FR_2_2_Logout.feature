# TC-FR-2.2 | Logout | Positif
# Verifikasi logout berhasil dilakukan oleh Pelajar secara otomatis menggunakan POM (Page Object Model)

Feature: Logout

  Background:
    Given user pelajar berada di halaman Login
    When memasukkan email pelajar "mohammad.amadeus.tif423@polban.ac.id" dan password valid "amadeus"
    And menekan tombol Masuk
    Then sistem berhasil autentikasi dan diarahkan ke Dashboard Pelajar

  Scenario: Verifikasi logout berhasil dilakukan oleh Pelajar
    When pelajar mengklik nama akun pada header
    And pelajar mengklik tombol Keluar pada dropdown
    Then sistem mengarahkan ke halaman login setelah logout
    And session pelajar telah dihapus setelah logout
    And akses langsung ke dashboard diredirect ke halaman login

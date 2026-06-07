Feature: Course Registration

  Background:
    Given user pelajar berada di halaman Login
    When memasukkan email pelajar "aliyashfi@gmail.com" dan password valid "aliyashfi"
    And menekan tombol Masuk
    Then sistem berhasil autentikasi dan diarahkan ke Dashboard Pelajar

  Scenario: Verifikasi enrollment course secara otomatis menggunakan input enrollment key
    Given user berada di halaman course overview untuk "Roman History 101"
    When user memasukkan kode pendaftaran "roman101"
    And menekan tombol Daftar
    Then muncul pop up sukses dengan pesan "Pendaftaran berhasil. Anda sekarang dapat mengakses materi dan kuis dari kursus ini."
    And klik tombol Tutup mengarahkan ke halaman detail course.

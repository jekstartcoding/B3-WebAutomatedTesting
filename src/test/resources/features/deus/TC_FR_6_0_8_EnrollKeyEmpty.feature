# TC-FR-6.0.8 | Course Overview | Negatif
# Verifikasi pendaftaran course gagal secara otomatis ketika field kode pendaftaran dibiarkan kosong

Feature: Course Overview

  Background:
    Given user pelajar berada di halaman Login
    When memasukkan email pelajar "mohammad.amadeus.tif423@polban.ac.id" dan password valid "amadeus"
    And menekan tombol Masuk
    Then sistem berhasil autentikasi dan diarahkan ke Dashboard Pelajar

  Scenario: Verifikasi pendaftaran course gagal ketika field kode pendaftaran kosong
    Given user pelajar berada di halaman course overview untuk course "Laravel Course"
    When user pelajar tidak mengisi kode pendaftaran dan menekan tombol Daftar
    Then sistem menampilkan pesan error "Silakan masukkan kode pendaftaran!"
    And user tetap berada di halaman Course Overview

# TC-FR-6.0.7 | Course Overview | Negatif
# Verifikasi pendaftaran course gagal karena kode pendaftaran salah

Feature: Course Overview

  Background:
    Given user berada di halaman login dengan URL "https://polban-space.cloudias79.com/jtk-learn/"
    When memasukkan email pelajar "muhammad.hasbi.tif423@polban.ac.id" dan password valid "gatot123"
    And menekan tombol Masuk
    Then sistem berhasil autentikasi dan diarahkan ke Dashboard Pelajar

  Scenario: Pendaftaran course gagal karena kode pendaftaran salah
    Given user berada di halaman course overview untuk "Memasak"
    When user memasukkan kode pendaftaran "kuncisalah999"
    And menekan tombol Daftar
    Then sistem menampilkan pesan error kode pendaftaran salah
    And user pelajar tetap berada di halaman Course Overview

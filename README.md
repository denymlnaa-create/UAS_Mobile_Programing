# UAS - Mobile Programing (EndemikDB)

Aplikasi **EndemikDB** adalah aplikasi Android berbasis Java yang berfungsi sebagai direktori data hewan dan tumbuhan endemik di Indonesia. Project ini dibuat untuk memenuhi tugas Ujian Akhir Semester (UAS) Mata Kuliah Pemrograman Mobile.

##  Fitur Utama
- **Daftar Endemik:** Menampilkan list data hewan dan tumbuhan endemik.
- **Pencarian:** Mencari data hewan atau tumbuhan berdasarkan nama secara real-time.
- **Detail Informasi:** Menampilkan deskripsi lengkap dan foto dari setiap entri.
- **Sistem Favorit:** Menyimpan data ke dalam daftar favorit menggunakan database lokal.
- **Mode Gelap/Terang:** Fitur toggle tema (Dark Mode & Light Mode) untuk kenyamanan pengguna.
- **Profil Pengguna:** Halaman profil yang menampilkan informasi pengembang.

##  Tech Stack & Library
- **Bahasa:** Java
- **UI Framework:** Material Design Components & Jetpack AndroidX
- **Database:** [Room Persistence Library](https://developer.android.com/training/data-storage/room) (Penyimpanan lokal)
- **Image Loading:** [Glide](https://github.com/bumptech/glide) (Manajemen gambar)
- **Networking:** Retrofit & Gson (Persiapan integrasi API)
- **Navigation:** BottomNavigationView & Fragment Management

## Struktur Project
- `EndemikFragment`: Fragment utama untuk list data (Hewan & Tumbuhan).
- `SearchActivity`: Aktivitas khusus untuk fitur pencarian.
- `DetailActivity`: Menampilkan rincian data yang dipilih.
- `FavoriteActivity`: Mengelola data yang ditandai sebagai favorit oleh pengguna.
- `ProfileFragment`: Informasi personal pemilik aplikasi.
- `AppDatabase`: Konfigurasi database Room untuk entitas `Endemik` dan `Favorit`.

## Cara Menjalankan
1. Clone repository ini.
2. Buka project menggunakan **Android Studio (Ladybug atau versi terbaru)**.
3. Sinkronisasi Gradle (Gradle Sync).
4. Jalankan aplikasi di Emulator atau perangkat fisik Android (Minimum SDK 24 / Android 7.0).

## Author
**Andika Deny Maulana**
- Prodi: Sistem Informasi
- Universitas: UPN Veteran Jakarta
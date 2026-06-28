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

## ScreenShot
<table>
  <tr>
    <td align="center">
      <b>Splash Screen</b><br>
      <img width="360" height="800" alt="SplashScreen" src="https://github.com/user-attachments/assets/67dab8c2-5fad-4c10-8c11-013b519b4f4d" />
    </td>
    <td align="center">
      <b>Home (Dark Mode)</b><br>
      <img width="360" height="800" alt="Home (Darkmode)" src="https://github.com/user-attachments/assets/43accfb9-9754-4af5-b95d-689d215b6730" />
    </td>
  </tr>
  <tr>
    <td align="center">
      <b>Tumbuhan Screen</b><br>
      <img width="360" height="800" alt="Tumbuhan" src="https://github.com/user-attachments/assets/2268a874-4342-40e5-b425-b4c03cf88741" />
    </td>
    <td align="center">
      <b>Search Screen</b><br>
      <img width="360" height="800" alt="Search" src="https://github.com/user-attachments/assets/afa98deb-ab8c-4f61-a107-d2367cf583b4" />
    </td>
  </tr>
  <tr>
    <td align="center">
      <b>Detail Screen</b><br>
      <img width="360" height="800" alt="DetailScreen" src="https://github.com/user-attachments/assets/71d767bd-977d-4c5a-9281-3648d06c6eeb" />
    </td>
    <td align="center">
      <b>Favorite Screen</b><br>
      <img width="360" height="800" alt="FavScreen" src="https://github.com/user-attachments/assets/00e530b9-d70b-4247-938d-778f95b324f1" />
    </td>
  </tr>
  <tr>
    <td align="center">
      <b>Profile Screen</b><br>
      <img width="360" height="800" alt="ProfileScreen" src="https://github.com/user-attachments/assets/27fcf8f7-3768-4db6-9c28-16727bc2e063" />
    </td>
    <td align="center">
      <b>Home (Light Mode)</b><br>
      <img width="360" height="800" alt="Home (Lightmode)" src="https://github.com/user-attachments/assets/f2930cc3-b4ba-4129-a065-72146003cd90" />
    </td>
  </tr>
</table>

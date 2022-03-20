# Submission Kelas Dicoding : Belajar Fundamental Aplikasi Android (BFAA)

## ⚠️ Disclaimer
> Repository ini ditunjukan sebagai **`REFERENSI`** saja dari submission kelas Dicoding [Belajar Fundamental Aplikasi Android (BFAA)][dicodingClass]. Segala bentuk plagiasi untuk submission BFAA dicoding yang dilakukan dari repository ini akan dikenakan sanksi oleh Dicoding

## 👉 Petunjuk
- Uji Coba aplikasi dapat diunduh pada halaman [berikut][appURL].
- Gunakan Token API Github (Github Personal Token) milik kamu sendiri untuk dapat mengakses data di repository ini
- Token Github API dapat dibuat pada halaman [setting][settingToken] atau dapat melihat tutorial [berikut][dicodingTutorialAPI] 
- Ganti Token API pada file `gradle.properties` seperti berikut. Halaman file [gradle.properties][fileGradleProperties] 
    ```gradle
    ..
    GITHUB_TOKEN = "<YOUR_API_TOKEN>" // ubah menjadi "ghp_Laxxxxxx..."
    ..
    ```
- Repository ini memuat 3 project submission (submission 1, 2 dan 3). Masing-masing submsission dikelompokan menjadi branch berikut.
    | Submission | Branch | Review Rating |
    | ------ | ------ | ------ |
    | Submission 1 | [Branch Submission 1][branch1] | ⭐⭐⭐⭐ |
    | Submission 2 | [Branch Submission 2][branch2] | ⭐⭐⭐⭐⭐ |
    | Submission 3 | [Branch Submission 3][branch3] | ⭐⭐⭐⭐⭐ |

## 📱 Gambaran Aplikasi (Submission 3, Complete)
### 🌞 Light Mode
<div>
  <img src="https://www.dropbox.com/s/cs873q4st8q0jf8/splashScreenLight.png?raw=1" alt="Splash Screen Light" style="width:220px;"/>
  <img src="https://www.dropbox.com/s/0q0nq4u3i0ysq6s/homeLight.png?raw=1" alt="Homescreen Layout" style="width:220px;"/>
  <img src="https://www.dropbox.com/s/3d3wndkw2o4nu3d/search.png?raw=1" alt="User Search Page" style="width:220px;"/>
  <img src="https://www.dropbox.com/s/khv0ku30vq4ku88/detailLight.png?raw=1" alt="User Detail Page" style="width:220px;"/>
  <img src="https://www.dropbox.com/s/z3u85lpqeixrncb/detailLikeLight.png?raw=1" alt="User Detail Liked Page" style="width:220px;"/>
  <img src="https://www.dropbox.com/s/a0phjlyjt4ehyim/favorite.png?raw=1" alt="User Favorite Page" style="width:220px;"/>
  <img src="https://www.dropbox.com/s/7bofezwofjuay7q/settingLight.png?raw=1" alt="User Setting Page" style="width:220px;"/>
</div>

### 🌙 Dark Mode
<div>
  <img src="https://www.dropbox.com/s/6s928g72yfh18a1/splashScreenDark.png?raw=1" alt="Splash Screen Light" style="width:220px;"/>
  <img src="https://www.dropbox.com/s/ji3nznkz9gouq25/homeDark.png?raw=1" alt="Homescreen Layout" style="width:220px;"/>
  <img src="https://www.dropbox.com/s/030wmeri35h8aj5/detailDark.png?raw=1" alt="User Detail Liked Page" style="width:220px;"/>
  <img src="https://www.dropbox.com/s/t83phi1qpklh341/favortieRemoveDark.png?raw=1" alt="User Favorite Page" style="width:220px;"/>
  <img src="https://www.dropbox.com/s/72w939fbwhdgg1x/settingDark.png?raw=1" alt="User Setting Page" style="width:220px;"/>
</div>


## 🚧 Requirements Submission
### 1️⃣ Submission 1
- [x] Tampilkan 10 item data user 
- [x] Penggunaan RecyclerView untuk menampilkan list user
- [x] Menampilkan circle avatar user
- [x] Penggunaan Parcelable pada data class
- [x] Non-nested view grup, viewgroup hanya menggunakan Constraint Layout
- [x] Pemanfaatan Intent Explicit untuk berpindah antar activity
- [x] **[Additional Features]** Fitur share user & Redirect ke halaman profile github dengan Intent Implicit
- [x] **[Additional Features]** UI adaptif dengan tampilan dark mode / light mode

### 2️⃣ Submission 2
- [x] Semua fitur Submission 1
- [x] Penerapan Android Modern Developement dengan MVVP (Model - View - ViewModel) pada arsitektur & package
- [x] Penggunaan REST API Github untuk menampilkan daftar user github (dengan library Reftrofit2)
- [x] Penggunaan ViewBinding pada layout
- [x] Penggunaan View Tab Layout untuk menampilkan following & followers user github
- [x] Penambahan fitur loading saat data dimuat (data detail, following & followers user)
- [x] **[Additional Features]** SplashScreen Activity
- [x] **[Additional Features]** Custom toolbar dengan tambahan icon & menu

### 3️⃣ Submission 3
- [x] Semua fitur Submission 1 & 2
- [x] Penerapan local persistent dengan Data Store & Room Database
- [x] Tamabahan fitur like / favoritekan user github dengan penyimpanan internal aplikasi menggunakan Room Database
- [x] Halaman Favorit untuk menampilkan user favorit
- [x] Pemilihan tema aplikasi, mode auto, dark mode atau light mode dengan Data Store
- [x] **[Additional Features]** Explorasi fitur RecyclerView dengan ItemTouchHelper, swipe ke kiri untuk menghapus item
- [x] **[Additional Features]** Tambahan mode auto pada tema perangkat, menyesuaikan tema device (android versi 8+)
- [x] **[Additional Features]** Penerapan custom font untuk aplikasi

## 🏆 MAD Score
Modern Android Developement (MAD) Score adalah scorecard / acuan penilaian android untuk menunjukan seberapa modern dari teknologi yang kamu adaptasi [(Android)](https://developer.android.com/modern-android-development/scorecard?hl=id)
<a href="https://madscorecard.withgoogle.com/scorecard/share/4197000331/">![summary](https://user-images.githubusercontent.com/47800225/159169005-939e75dd-ce5c-4e7e-807a-6567afbab6f1.png)</a>



[dicodingClass]: <https://www.dicoding.com/academies/14>
[appURL]: <https://www.dropbox.com/s/lbyxu7woltwjxfh/app-debug.apk?dl=0s>
[settingToken]: <https://github.com/settings/tokens>
[dicodingTutorialAPI]: <https://www.dicoding.com/blog/apa-itu-rate-limit-pada-github-api/>
[fileGradleProperties]: <https://github.com/apriantoa917/android-dicoding-BFAA/blob/submission3/gradle.properties>
[branch1]: <https://github.com/apriantoa917/android-dicoding-BFAA/tree/submission1>
[branch2]: <https://github.com/apriantoa917/android-dicoding-BFAA/tree/submission2>
[branch3]: <https://github.com/apriantoa917/android-dicoding-BFAA/tree/submission3>

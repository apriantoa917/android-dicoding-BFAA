package com.aprianto.mygithub.ui.detail

import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.aprianto.mygithub.*
import com.aprianto.mygithub.data.model.UserFavorite
import com.aprianto.mygithub.data.viewmodel.UserDetailViewModel
import com.aprianto.mygithub.data.viewmodel.UserSocialViewModel
import com.aprianto.mygithub.data.viewmodel.ViewModelUserFactory
import com.aprianto.mygithub.databinding.ActivityDetailUserBinding
import com.aprianto.mygithub.ui.detail.social.UserSocialViewPagerAdapter
import com.aprianto.mygithub.utils.Constanta
import com.aprianto.mygithub.utils.Helper
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.NullPointerException
import java.util.*


class DetailUserActivity : AppCompatActivity() {

    private val socialViewModel: UserSocialViewModel by viewModels()
    private lateinit var detailViewModel: UserDetailViewModel
    private lateinit var username: String
    private lateinit var binding: ActivityDetailUserBinding
    private val mContext = this@DetailUserActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra(Constanta.EXTRA_USER).toString()

        TabLayoutMediator(
            binding.tabs,
            binding.viewPager.apply {
                this.adapter = UserSocialViewPagerAdapter(mContext, username)
            })
        { tab, position ->
            tab.text = resources.getString(Constanta.TAB_TITLES[position])
        }.attach()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // jika inflate menu options, tombol back harus hard code manual
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        detailViewModel = obtainViewModel(mContext)
        detailViewModel.apply {
            this.setUserData(username)
            this.checkFavorite(username).observe(mContext, { data ->
                // cek apakah user pernah like
                if (data != null) {
                    this.isFavorite.postValue(true)
                } else {
                    this.isFavorite.postValue(false)
                }
            })
            this.userData.observe(mContext, { data ->
                binding.apply {
                    tvUsername.text = StringBuilder("@").append(data.login)
                    tvName.text = getDefaultValue(data.name,"Name not set")
                    tvLocation.text = getDefaultValue(data.location)
                    tvCompany.text = getDefaultValue(data.company)
                    tvFollowers.text = data.followers.toString()
                    tvFollowing.text = data.following.toString()
                    tvRepository.text = data.publicRepos.toString()
                    Glide.with(mContext)
                        .load(data.avatarUrl)
                        .into(imgAvatar)
                    val favorite = UserFavorite(
                        data.login,
                        data.name,
                        data.avatarUrl,
                        Helper.getCurrentDate()
                    )
                    detailViewModel.initFavorite(favorite)
                }
            })
            this.error.observe(
                mContext,
                { Helper.toast(mContext, it) })
            this.isFavorite.observe(mContext, { state ->
                // jika sudah difavorit button fab berwarna merah, jika belum fab biru
                if (state) {
                    binding.btnFavorite.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                mContext,
                                R.color.favorite
                            )
                        )
                } else {
                    binding.btnFavorite.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            mContext,
                            R.color.github_cyan
                        )
                    )
                }
            })
        }

        socialViewModel.apply {
            this.error.observe(
                mContext,
                { Helper.toast(mContext, it) })
        }

        binding.btnFavorite.setOnClickListener {
            if (detailViewModel.isFavorite.value == true) {
                MaterialAlertDialogBuilder(mContext)
                    .setTitle("Hapus Favorite ?")
                    .setMessage(StringBuilder("@").append("$username sudah ada di favorite. Apakah anda ingin menghapusnya dari favorite?"))
                    .setCancelable(true)
                    .setIcon(R.drawable.ic_baseline_delete_24)
                    .setPositiveButton("Hapus") { _: DialogInterface, _: Int ->
                        detailViewModel.unsetFavorite(username)
                        Helper.toast(mContext, "$username dihapus dari favorite")
                    }
                    .setNegativeButton("Batalkan") { _: DialogInterface, _: Int -> }
                    .show()
            } else {
                detailViewModel.apply {
                    this.userFavorite.value?.let { favorite ->
                        this.setFavorite(favorite)
                        Helper.toast(mContext, "Berhasil menyukai $username")
                    }
                }
            }
        }
    }

    private fun getDefaultValue(value: String, defaultValue:String="-"):String{
        try {
            if (value.isNotEmpty()){
                return value
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
        return defaultValue

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_detail_user_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Halo! Saya menggunakan github sebagai ${username}. Anda bisa cek sekarang di link berikut : https://github.com/${username}"
                    )
                    type = "text/plain"
                }
                val openShareProfile = Intent.createChooser(sendIntent, null)
                startActivity(openShareProfile)
            }
            R.id.menu_profile -> {
                val url = "https://github.com/${username}"
                val openGithubProfile = Intent(Intent.ACTION_VIEW)
                openGithubProfile.data = Uri.parse(url)
                startActivity(openGithubProfile)

            }
            R.id.menu_repository -> {
                val url = "https://github.com/${username}?tab=repositories"
                val openGithubRepo = Intent(Intent.ACTION_VIEW)
                openGithubRepo.data = Uri.parse(url)
                startActivity(openGithubRepo)
            }
        }
        return true
    }

    private fun obtainViewModel(activity: AppCompatActivity): UserDetailViewModel {
        val factory = ViewModelUserFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[UserDetailViewModel::class.java]
    }
}
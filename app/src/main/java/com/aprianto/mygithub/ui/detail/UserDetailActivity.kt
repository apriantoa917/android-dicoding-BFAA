package com.aprianto.mygithub.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aprianto.mygithub.*
import com.aprianto.mygithub.data.viewmodel.UserDetailViewModel
import com.aprianto.mygithub.databinding.ActivityDetailUserBinding
import com.aprianto.mygithub.ui.detail.social.UserSocialViewPagerAdapter
import com.aprianto.mygithub.utils.Constanta
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*


class DetailUserActivity : AppCompatActivity() {

    private val viewModel: UserDetailViewModel by viewModels()
    private lateinit var username: String
    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra(Constanta.EXTRA_USER).toString()

        TabLayoutMediator(
            binding.tabs,
            binding.viewPager.apply {
                this.adapter = UserSocialViewPagerAdapter(this@DetailUserActivity, username)
            })
        { tab, position ->
            tab.text = resources.getString(Constanta.TAB_TITLES[position])
        }.attach()

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.let {
            it.elevation = 0f
            it.title = username.toLowerCase(Locale.ROOT)
        }

        viewModel.apply {
            this.setUserData(username)
            this.userData.observe(this@DetailUserActivity, { data ->
                binding.apply {
                    tvUsername.text = StringBuilder("@").append(data.login)
                    tvName.text = data.name
                    tvLocation.text = data.location
                    tvCompany.text = data.company
                    tvFollowers.text = data.followers.toString()
                    tvFollowing.text = data.following.toString()
                    tvRepository.text = data.publicRepos.toString()
                    Glide.with(this@DetailUserActivity)
                        .load(data.avatarUrl)
                        .into(imgAvatar)
                }
            })
            this.error.observe(
                this@DetailUserActivity,
                { Constanta.toastError(this@DetailUserActivity, it) })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
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
                return true
            }
            R.id.menu_profile -> {
                val url = "https://github.com/${username}"
                val openGithubProfile = Intent(Intent.ACTION_VIEW)
                openGithubProfile.data = Uri.parse(url)
                startActivity(openGithubProfile)
                return true
            }
            R.id.menu_repository -> {
                val url = "https://github.com/${username}?tab=repositories"
                val openGithubRepo = Intent(Intent.ACTION_VIEW)
                openGithubRepo.data = Uri.parse(url)
                startActivity(openGithubRepo)
                return true
            }
            else -> return true
        }
    }
}
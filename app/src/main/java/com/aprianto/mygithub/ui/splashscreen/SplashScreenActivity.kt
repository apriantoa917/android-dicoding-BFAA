package com.aprianto.mygithub.ui.splashscreen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.aprianto.mygithub.data.viewmodel.UserSettingViewModel
import com.aprianto.mygithub.data.viewmodel.ViewModelSettingFactory
import com.aprianto.mygithub.databinding.ActivitySplashScreenBinding
import com.aprianto.mygithub.ui.search.UserSearchActivity
import com.aprianto.mygithub.ui.setting.dataStore
import com.aprianto.mygithub.utils.SettingPreferences
import java.util.*
import kotlin.concurrent.schedule

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(dataStore)
        val settingViewModel =
            ViewModelProvider(this, ViewModelSettingFactory(pref))[UserSettingViewModel::class.java]

        settingViewModel.getThemeSettings().observe(this, {
            when (it) {
                0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        })

        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Timer().schedule(1500) {
            startActivity(Intent(this@SplashScreenActivity, UserSearchActivity::class.java))
            finish()
        }
    }
}
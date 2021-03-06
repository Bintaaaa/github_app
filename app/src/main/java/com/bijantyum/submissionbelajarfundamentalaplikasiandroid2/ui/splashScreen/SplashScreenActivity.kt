package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.splashScreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.databinding.ActivitySplashScreeenBinding
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.helper.ViewModelFactory
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.main.MainActivity
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.menu.settings.SettingPreferences
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.menu.settings.SettingViewModel

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreeenBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreeenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(dataStore)
        val viewModel =
            ViewModelProvider(this, ViewModelFactory(pref)).get(SettingViewModel::class.java)

        viewModel.getThemeSettings().observe(this, { isDarkModeActive ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        })
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, DELAY)

    }

    companion object{
        private const val DELAY = 3000L
    }
}